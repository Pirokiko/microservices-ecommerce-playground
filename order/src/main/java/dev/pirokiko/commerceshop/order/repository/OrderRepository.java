package dev.pirokiko.commerceshop.order.repository;

import dev.pirokiko.commerceshop.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);

    @Query("SELECT o FROM Order o INNER JOIN o.items oi WHERE sum(oi.productCost * oi.quantity) > :minCost")
    List<Order> findWithMinCost(Long minCost);

    @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :orderId")
    Optional<Order> findByIdWithItems(Long orderId);
}
