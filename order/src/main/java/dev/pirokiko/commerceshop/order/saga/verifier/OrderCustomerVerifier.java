package dev.pirokiko.commerceshop.order.saga.verifier;

import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderCustomerMessage;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderCustomerResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class OrderCustomerVerifier {
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Map<Long, CompletableFuture<Boolean>> callbackMap = new HashMap<>();

    public OrderCustomerVerifier(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public CompletableFuture<@NotNull Boolean> verify(final Long orderId) {
        CompletableFuture<Boolean> cf = new CompletableFuture<>();

        Order order = orderRepository.findById(orderId).orElseThrow();

        String exchange = RabbitConfigStatics.Exchanges.EXCHANGE_NAME;
        String routingKey = RabbitConfigStatics.RoutingKeys.VERIFY_ORDER_CUSTOMER_REQUEST;
        rabbitTemplate.convertAndSend(exchange, routingKey, VerifyOrderCustomerMessage.builder().orderId(orderId).customerId(order.getCustomerId()).build());

        callbackMap.put(orderId, cf);

        return cf;
    }

    public void handleVerificationMessage(VerifyOrderCustomerResultMessage message) {
        CompletableFuture<Boolean> cf = callbackMap.get(message.getOrderId());
        if (cf == null) {
            throw new InvalidParameterException();
        }
        cf.complete(message.getVerified());
    }
}
