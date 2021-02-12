package dev.pirokiko.commerceshop.order.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateOrderDto {
    @NotNull
    @Min(1L)
    private Long customerId;

    @NotNull
    @Size(min = 1)
    private List<CreateOrderItemDto> items;
}
