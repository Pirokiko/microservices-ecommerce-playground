package dev.pirokiko.commerceshop.order.controller;

import dev.pirokiko.commerceshop.order.dto.CreateOrderDto;
import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import dev.pirokiko.commerceshop.order.saga.OrderCreationSaga;
import dev.pirokiko.commerceshop.order.service.PaymentCreationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderCreationSaga orderCreationSaga;
    private final PaymentCreationService paymentCreationService;
    private final OrderRepository orderRepository;

    public OrderController(OrderCreationSaga orderCreationSaga, PaymentCreationService paymentCreationService, OrderRepository orderRepository) {
        this.orderCreationSaga = orderCreationSaga;
        this.paymentCreationService = paymentCreationService;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderDto orderDto){
        return orderCreationSaga.createOrder(orderDto);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    @PostMapping("/{orderId}/payment")
    public ResponseEntity<Void> startPayment(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        // Asynchronously creates a payment, so respond without data
        paymentCreationService.createPayment(order);

        return ResponseEntity.accepted().build();
    }
}
