package dev.pirokiko.commerceshop.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pirokiko.commerceshop.order.dto.CreateOrderDto;
import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.entity.OrderItem;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderCreationService {

    private final OrderRepository orderRepository;
    private final OrderNumberGenerator orderNumberGenerator;
    private final ObjectMapper objectMapper;

    public OrderCreationService(OrderRepository orderRepository, OrderNumberGenerator orderNumberGenerator, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.orderNumberGenerator = orderNumberGenerator;
        this.objectMapper = objectMapper;
    }

    public Order createOrder(CreateOrderDto orderDto) {
        Order order = new Order();
        order.setCustomerId(orderDto.getCustomerId());
        order.setOrderNumber(orderNumberGenerator.generateOrderNumer());

        order.setItems(
                orderDto.getItems().stream()
                        .map(item -> objectMapper.convertValue(item, OrderItem.class))
                        .collect(Collectors.toList())
        );

        return orderRepository.saveAndFlush(order);
    }
}
