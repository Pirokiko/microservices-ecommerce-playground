package dev.pirokiko.commerceshop.order.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_seq")
    @SequenceGenerator(name = "order_item_id_seq", sequenceName = "order_item_id_seq")
    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Double productCost;

    @NotNull
    private Long quantity;

    public Double getCost(){
        return this.quantity * this.productCost;
    }
}
