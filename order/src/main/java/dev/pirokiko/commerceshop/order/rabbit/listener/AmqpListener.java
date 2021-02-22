package dev.pirokiko.commerceshop.order.rabbit.listener;

import dev.pirokiko.commerceshop.order.saga.verifier.OrderCustomerVerifier;
import dev.pirokiko.commerceshop.order.service.PaymentCreationService;
import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import dev.pirokiko.commerceshop.rabbit.message.PaymentCreatedMessage;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderCustomerResultMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

@RabbitListener(queues = {RabbitConfigStatics.Queues.ORDER_QUEUE_NAME})
public class AmqpListener {

    private final PaymentCreationService paymentCreationService;
    private final OrderCustomerVerifier orderCustomerVerifier;

    public AmqpListener(PaymentCreationService paymentCreationService, OrderCustomerVerifier orderCustomerVerifier) {
        this.paymentCreationService = paymentCreationService;
        this.orderCustomerVerifier = orderCustomerVerifier;
    }

    @RabbitHandler
    public void handlePaymentCreatedMessage(PaymentCreatedMessage message) {
        paymentCreationService.handlePaymentCreatedMessage(message);
    }

    @RabbitHandler
    public void handleVerificationMessage(VerifyOrderCustomerResultMessage message) {
        orderCustomerVerifier.handleVerificationMessage(message);
    }
}
