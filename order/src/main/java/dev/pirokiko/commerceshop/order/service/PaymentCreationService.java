package dev.pirokiko.commerceshop.order.service;

import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.enums.PaymentStatus;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import dev.pirokiko.commerceshop.rabbit.message.CreatePaymentMessage;
import dev.pirokiko.commerceshop.rabbit.message.PaymentCreatedMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentCreationService {

    private final AmqpTemplate amqpTemplate;
    private final OrderRepository orderRepository;

    public PaymentCreationService(AmqpTemplate amqpTemplate, OrderRepository orderRepository) {
        this.amqpTemplate = amqpTemplate;
        this.orderRepository = orderRepository;
    }

    @Async
    public void createPayment(Order order) {
        amqpTemplate.convertAndSend(
                RabbitConfigStatics.RoutingKeys.PAYMENT_CREATE_REQUEST,
                CreatePaymentMessage.builder()
                        .orderId(order.getId())
                        .orderNumber(order.getOrderNumber())
                        .amount(order.getCost())
                        .customerId(order.getCustomerId())
                        .build()
        );
    }

    @Transactional
    public void handlePaymentCreatedMessage(PaymentCreatedMessage message) {
        Order order = orderRepository.findById(message.getOrderId()).orElseThrow();

        order.setPaymentId(message.getId());
        order.setPaymentStatus(PaymentStatus.valueOf(message.getStatus().name()));

        orderRepository.flush();
    }
}
