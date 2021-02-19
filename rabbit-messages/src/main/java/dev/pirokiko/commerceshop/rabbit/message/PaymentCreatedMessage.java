package dev.pirokiko.commerceshop.rabbit.message;

import java.io.Serializable;
import java.util.UUID;

public class PaymentCreatedMessage implements Serializable {
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
