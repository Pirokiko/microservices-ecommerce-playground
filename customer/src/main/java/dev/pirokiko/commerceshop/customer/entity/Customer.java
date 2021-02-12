package dev.pirokiko.commerceshop.customer.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Table(uniqueConstraints = {@UniqueConstraint(name = "uniuque_email", columnNames = {"email"})})
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq")
    private Long id;

    @Email
    @NotNull
    private String email;

    private String name;

    @OneToMany
    private List<Address> addresses = new ArrayList<>();
}
