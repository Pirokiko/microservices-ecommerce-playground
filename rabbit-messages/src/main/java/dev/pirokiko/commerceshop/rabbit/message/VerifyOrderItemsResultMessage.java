package dev.pirokiko.commerceshop.rabbit.message;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@SuperBuilder
public class VerifyOrderItemsResultMessage implements Serializable {
    private final Long orderId;
    private final List<VerifyOrderItemsMessage.OrderItemDto> items;
    private final Boolean verified;

    @Getter
    @SuperBuilder
    public static class OrderItemDto {
        private final Long productId;
        private final Double cost;
        private final Long quantity;
    }
}
