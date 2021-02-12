package dev.pirokiko.commerceshop.order.controller;

import dev.pirokiko.commerceshop.order.dto.CreateOrderDto;
import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.saga.OrderCreationSaga;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderCreationSaga orderCreationSaga;

    public OrderController(OrderCreationSaga orderCreationSaga) {
        this.orderCreationSaga = orderCreationSaga;
    }

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderDto orderDto){
        return orderCreationSaga.createOrder(orderDto);
    }
}
