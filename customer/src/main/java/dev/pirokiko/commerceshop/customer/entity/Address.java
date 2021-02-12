package dev.pirokiko.commerceshop.customer.entity;

import dev.pirokiko.commerceshop.customer.validation.ValidNLZipCode;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq")
    private Long id;

    @NotNull
    private String street;

    @NotNull
    private Integer houseNumber;

    private String houseNumberAddition;

    @NotNull
    @ValidNLZipCode
    private String zipCode;
}
