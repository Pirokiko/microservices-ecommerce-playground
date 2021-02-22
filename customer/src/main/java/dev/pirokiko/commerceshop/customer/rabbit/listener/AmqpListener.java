package dev.pirokiko.commerceshop.customer.rabbit.listener;

import dev.pirokiko.commerceshop.customer.rabbit.handler.OrderCustomerVerification;
import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderCustomerMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

public class AmqpListener {
    private final OrderCustomerVerification orderCustomerVerification;

    public AmqpListener(OrderCustomerVerification orderCustomerVerification) {
        this.orderCustomerVerification = orderCustomerVerification;
    }

    @RabbitListener(queues = {RabbitConfigStatics.Queues.CUSTOMER_QUEUE_NAME})
    public void handleCustomerVerification(@Payload VerifyOrderCustomerMessage message) {
        this.orderCustomerVerification.handleCustomerVerification(message);
    }

}
