package dev.pirokiko.commerceshop.order.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerAddressDto {
    private Long id;
    private String street;
    private Integer houseNumber;
    private String houseNumberAddition;
    private String zipCode;
}
