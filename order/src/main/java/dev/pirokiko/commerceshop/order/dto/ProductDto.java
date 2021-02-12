package dev.pirokiko.commerceshop.order.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String barcode;

    @NotNull
    private Double cost;
}
