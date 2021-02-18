package dev.pirokiko.commerceshop.payment.dto;

import dev.pirokiko.commerceshop.payment.enums.PaymentStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentDto {
    private final UUID id;

    private final Double amount;

    private final PaymentStatus status;
}
