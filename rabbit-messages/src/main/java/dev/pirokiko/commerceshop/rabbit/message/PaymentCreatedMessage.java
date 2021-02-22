package dev.pirokiko.commerceshop.rabbit.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
public class PaymentCreatedMessage {
    private Long orderId;

    private UUID id;

    private Double amount;

    private PaymentStatus status;

    private Double amountPayed;

    public enum PaymentStatus {
        INITIALIZED,
        PAYMENT_STARTED,
        PAYMENT_PROCESSING,
        PAYMENT_COMPLETED,
        PAYMENT_FAILED,
    }
}
