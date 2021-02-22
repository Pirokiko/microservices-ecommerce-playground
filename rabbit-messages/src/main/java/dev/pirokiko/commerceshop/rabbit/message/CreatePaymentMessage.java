package dev.pirokiko.commerceshop.rabbit.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CreatePaymentMessage {
    private Long orderId;
    private String orderNumber;
    private Long customerId;

    private Double amount;
}
