package dev.pirokiko.commerceshop.order.saga;

import dev.pirokiko.commerceshop.order.dto.CreateOrderDto;
import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import dev.pirokiko.commerceshop.order.saga.verifier.OrderCreationVerifier;
import dev.pirokiko.commerceshop.order.service.OrderCreationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreationSaga {
    private final OrderCreationService orderCreationService;
    private final OrderCreationVerifier orderCreationVerifier;
    private final OrderRepository orderRepository;

    public OrderCreationSaga(
            OrderCreationService orderCreationService, OrderCreationVerifier orderCreationVerifier, OrderRepository orderRepository) {
        this.orderCreationService = orderCreationService;
        this.orderCreationVerifier = orderCreationVerifier;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(CreateOrderDto createOrderDto) {
        final Order order = orderCreationService.createOrder(createOrderDto);

        // Performed asynchronously
        orderCreationVerifier.verifyAsync(order.getId())
                .thenApplyAsync(orderVerificationDto -> {
                    // Save verification
                    orderVerificationDto.getOrder().setVerified(orderVerificationDto.getVerified());
                    orderRepository.flush();
                    return orderVerificationDto.getOrder().getId();
                })
                .whenComplete((orderId, error) -> {
                    if (error != null) {
                        log.error("Error occurred during async processing", error);
                    }
                });

        return order;
    }
}
