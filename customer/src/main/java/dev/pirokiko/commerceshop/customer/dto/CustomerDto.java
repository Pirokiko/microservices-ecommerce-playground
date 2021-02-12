package dev.pirokiko.commerceshop.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerDto {
    private Long id;

    @NotNull
    private String email;

    private String name;
}
