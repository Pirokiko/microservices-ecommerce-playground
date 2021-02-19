package dev.pirokiko.commerceshop.inventory.entity;

import lombok.Data;
import org.hibernate.validator.constraints.EAN;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_id_seq")
    @SequenceGenerator(name = "inventory_id_seq", sequenceName = "inventory_id_seq")
    private Long id;

    @OneToOne
    private Product product;

    @Min(value = 0L)
    private Long available;
}
