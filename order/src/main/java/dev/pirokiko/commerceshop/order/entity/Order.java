package dev.pirokiko.commerceshop.order.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "\"order\"")
@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq")
    private Long id;

    @NotNull
    private String orderNumber;

    @NotNull
    private Long customerId;

    // Managed in the payment micro-service (updated through messaging)
    private Long paymentId;
    private Long paymentStatus;

    private Boolean verified;

    @OneToMany
    @Cascade(value = { org.hibernate.annotations.CascadeType.ALL })
    private List<OrderItem> items;

    public Double getCost(){
        return items.stream().map(OrderItem::getCost).reduce(Double::sum).orElse(0.0);
    }
}
