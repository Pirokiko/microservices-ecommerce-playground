package dev.pirokiko.commerceshop.customer.rabbit.listener;

import dev.pirokiko.commerceshop.customer.entity.Customer;
import dev.pirokiko.commerceshop.customer.repository.CustomerRepository;
import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderCustomerMessage;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderCustomerResultMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RabbitListener(queues = RabbitConfigStatics.Queues.CUSTOMER_QUEUE_NAME)
public class OrderCustomerVerification {

    private final RabbitTemplate rabbitTemplate;

    private final CustomerRepository customerRepository;

    public OrderCustomerVerification(RabbitTemplate rabbitTemplate, CustomerRepository customerRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.customerRepository = customerRepository;
    }

    @RabbitHandler
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
