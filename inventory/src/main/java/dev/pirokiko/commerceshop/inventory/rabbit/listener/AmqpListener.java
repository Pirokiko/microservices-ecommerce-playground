package dev.pirokiko.commerceshop.inventory.rabbit.listener;

import dev.pirokiko.commerceshop.inventory.rabbit.handler.OrderItemsVerification;
import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderItemsMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

@RabbitListener(queues = {RabbitConfigStatics.Queues.INVENTORY_QUEUE_NAME})
public class AmqpListener {
    private final OrderItemsVerification orderItemsVerification;

    public AmqpListener(OrderItemsVerification orderItemsVerification) {
        this.orderItemsVerification = orderItemsVerification;
    }

    @RabbitHandler
    public void verifyOrdersItems(@Payload VerifyOrderItemsMessage message) {
        orderItemsVerification.verifyOrdersItems(message);
    }
}
