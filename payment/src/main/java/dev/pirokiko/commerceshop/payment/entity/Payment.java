package dev.pirokiko.commerceshop.payment.entity;

import dev.pirokiko.commerceshop.payment.enums.PaymentStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue
    private UUID id;

    private Double amount;

    private PaymentStatus status;

    private Double amountPayed;
}
