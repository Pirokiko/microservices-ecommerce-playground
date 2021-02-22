package dev.pirokiko.commerceshop.payment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreatePaymentDto {
    @NotNull
    private Long orderId;
    @NotNull
    @Min(value = 0)
    private Double amount;
}
