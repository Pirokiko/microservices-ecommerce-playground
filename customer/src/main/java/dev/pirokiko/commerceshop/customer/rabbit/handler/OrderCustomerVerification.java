package dev.pirokiko.commerceshop.customer.rabbit.handler;

import dev.pirokiko.commerceshop.customer.entity.Customer;
import dev.pirokiko.commerceshop.customer.repository.CustomerRepository;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderCustomerMessage;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderCustomerResultMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class OrderCustomerVerification {

    private final RabbitTemplate rabbitTemplate;

    private final CustomerRepository customerRepository;

    public OrderCustomerVerification(RabbitTemplate rabbitTemplate, CustomerRepository customerRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.customerRepository = customerRepository;
    }

    public void handleCustomerVerification(VerifyOrderCustomerMessage message) {
        Customer customer = customerRepository.findById(message.getCustomerId()).orElse(null);

        Boolean verified = customer != null;

        rabbitTemplate.convertAndSend("verify.order.customer.response", VerifyOrderCustomerResultMessage.builder()
                .orderId(message.getOrderId())
                .customerId(message.getCustomerId())
                .verified(verified)
                .build());
    }
}
