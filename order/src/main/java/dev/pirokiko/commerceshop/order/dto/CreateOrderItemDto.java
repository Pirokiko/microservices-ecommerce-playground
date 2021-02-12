package dev.pirokiko.commerceshop.order.dto;

import lombok.Data;

@Data
public class CreateOrderItemDto {
    private Long productId;
    private Double productCost; // This will be verified/updated through messaging
    private Long quantity;
}
