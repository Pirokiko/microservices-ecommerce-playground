package dev.pirokiko.commerceshop.order.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDto {
    private Long id;
    private String email;
    private String name;
    private List<CustomerAddressDto> addresses = new ArrayList<>();
}
