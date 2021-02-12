package dev.pirokiko.commerceshop.inventory.entity;

import lombok.Data;
import org.hibernate.validator.constraints.EAN;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq")
    private Long id;

    @NotNull
    private String name;

    @EAN
    @NotNull
    private String barcode;

    @Min(value = 0L)
    @NotNull
    private Double cost;
}
