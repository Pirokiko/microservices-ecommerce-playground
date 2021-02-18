package dev.pirokiko.commerceshop.order.dto;

import dev.pirokiko.commerceshop.order.entity.Order;
import lombok.Data;

@Data
public class OrderVerificationDto {
    private final Order order;
    private final Boolean verified;
}
