package dev.pirokiko.commerceshop.order.repository;

import dev.pirokiko.commerceshop.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
