package dev.pirokiko.commerceshop.inventory.rabbit.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pirokiko.commerceshop.inventory.entity.Inventory;
import dev.pirokiko.commerceshop.inventory.repository.InventoryRepository;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderItemsMessage;
import dev.pirokiko.commerceshop.rabbit.message.VerifyOrderItemsResultMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RabbitListener
public class OrderItemsVerification {

    private final String routingKeyResponse = "verify.order.response";

    private final AmqpTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final InventoryRepository inventoryRepository;

    public OrderItemsVerification(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, InventoryRepository inventoryRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.inventoryRepository = inventoryRepository;
    }

    @RabbitHandler
    public void verifyOrdersItems(VerifyOrderItemsMessage message) {
        Boolean verified = message.getItems().stream().reduce(true, (curr, itemDto) -> {
            if (!curr) return false;
            Inventory inventory = inventoryRepository.findByProductId(itemDto.getProductId());
            return inventory.getAvailable() >= itemDto.getQuantity();
        }, (a, b) -> a && b);

        if (verified) {
            for (VerifyOrderItemsMessage.OrderItemDto item : message.getItems()) {
                Inventory inventory = inventoryRepository.findByProductId(item.getProductId());
                inventory.setAvailable(inventory.getAvailable() - item.getQuantity());
            }
            inventoryRepository.flush();
        }


        // Return a response
        VerifyOrderItemsResultMessage resultMessage = VerifyOrderItemsResultMessage.builder()
                .orderId(message.getOrderId())
                .items(message.getItems())
                .verified(verified)
                .build();
        objectMapper.convertValue(message, VerifyOrderItemsResultMessage.class);
        rabbitTemplate.convertAndSend(routingKeyResponse, resultMessage);
    }
}
