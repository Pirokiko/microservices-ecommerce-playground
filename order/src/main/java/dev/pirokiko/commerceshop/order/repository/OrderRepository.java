package dev.pirokiko.commerceshop.order.repository;

import dev.pirokiko.commerceshop.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);

    @Query("SELECT o FROM Order o INNER JOIN o.items oi WHERE sum(oi.productCost * oi.quantity) > :minCost")
    List<Order> findWithMinCost(Long minCost);

    @Query("SELECT o FROM Order o INNER join o.items oi WHERE oi.productId = :productId")
    List<Order> findWithProduct(Long productId);
}
