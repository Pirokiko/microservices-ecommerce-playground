package dev.pirokiko.commerceshop.rabbit.message;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@SuperBuilder
public class VerifyOrderItemsMessage implements Serializable {
    private final Long orderId;
    private final List<OrderItemDto> items;

    @Getter
    @SuperBuilder
    public static class OrderItemDto {
        private final Long productId;
        private final Double cost;
        private final Long quantity;
    }
}
