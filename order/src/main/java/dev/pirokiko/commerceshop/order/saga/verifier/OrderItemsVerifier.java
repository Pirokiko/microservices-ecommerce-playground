package dev.pirokiko.commerceshop.order.saga.verifier;

import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderItemsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderItemsVerifier {
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    private final Map<Long, CompletableFuture<Boolean>> callbackMap = new HashMap<>();

    public OrderItemsVerifier(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public CompletableFuture<@NotNull Boolean> verify(final Long orderId) {
        CompletableFuture<Boolean> cf = new CompletableFuture<>();

        Order order = orderRepository.findByIdWithItems(orderId).orElseThrow();

        String exchange = RabbitConfigStatics.Exchanges.EXCHANGE_NAME;
        String routingKey = RabbitConfigStatics.RoutingKeys.VERIFY_ORDER_ITEMS_REQUEST;
        VerifyOrderItemsMessage message = VerifyOrderItemsMessage.builder()
                .orderId(orderId)
                .items(order.getItems().stream()
                        .map(item -> VerifyOrderItemsMessage.OrderItemDto.builder()
                                .productId(item.getProductId())
                                .cost(item.getProductCost())
                                .quantity(item.getQuantity())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        rabbitTemplate.convertAndSend(exchange, routingKey, message);

        callbackMap.put(orderId, cf);

        return cf;
    }
}
