package dev.pirokiko.commerceshop.order.service;

import dev.pirokiko.commerceshop.order.api.PaymentServiceApi;
import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import org.openapitools.client.model.PaymentDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentCreationService {

    private final PaymentServiceApi paymentApi;
    private final OrderRepository orderRepository;

    public PaymentCreationService(PaymentServiceApi paymentApi, OrderRepository orderRepository) {
        this.paymentApi = paymentApi;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public PaymentDto createPayment(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        PaymentDto paymentDto = paymentApi.createPayment(order);

        order.setPaymentId(paymentDto.getId());
        order.setPaymentStatus(paymentDto.getStatus());

        orderRepository.flush();

        return paymentDto;
    }
}
