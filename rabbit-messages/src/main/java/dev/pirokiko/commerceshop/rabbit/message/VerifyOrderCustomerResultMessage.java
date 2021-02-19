package dev.pirokiko.commerceshop.rabbit.message;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class VerifyOrderCustomerResultMessage implements Serializable {
    @NotNull
    private final Long orderId;
    @NotNull
    private final Long customerId;
    @NotNull
    private final Boolean verified;
}
