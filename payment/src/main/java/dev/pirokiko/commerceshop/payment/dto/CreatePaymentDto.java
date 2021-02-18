package dev.pirokiko.commerceshop.payment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreatePaymentDto {
    private Double amount;
}
