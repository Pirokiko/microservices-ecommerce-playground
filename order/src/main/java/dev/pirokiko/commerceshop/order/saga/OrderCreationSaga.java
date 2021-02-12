package dev.pirokiko.commerceshop.order.saga;

import dev.pirokiko.commerceshop.order.dto.CreateOrderDto;
import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import dev.pirokiko.commerceshop.order.saga.verifier.OrderCreationVerifier;
import dev.pirokiko.commerceshop.order.service.OrderCreationService;
import org.springframework.stereotype.Component;

@Component
public class OrderCreationSaga {
    private final OrderCreationService orderCreationService;
    private final OrderRepository orderRepository;
    private final OrderCreationVerifier orderCreationVerifier;

    public OrderCreationSaga(
            OrderCreationService orderCreationService,
            OrderRepository orderRepository,
            OrderCreationVerifier orderCreationVerifier
    ) {
        this.orderCreationService = orderCreationService;
        this.orderRepository = orderRepository;
        this.orderCreationVerifier = orderCreationVerifier;
    }

    public Order createOrder(CreateOrderDto createOrderDto) {
        Order order = orderCreationService.createOrder(createOrderDto);

        orderCreationVerifier.verify(order).whenComplete((verified, error) -> {
            order.setVerified(error == null && verified);
            // Save changes
            orderRepository.flush();
        });

        return order;
    }
}
