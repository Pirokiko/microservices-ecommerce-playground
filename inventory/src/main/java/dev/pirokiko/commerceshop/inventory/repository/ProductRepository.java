package dev.pirokiko.commerceshop.inventory.repository;

import dev.pirokiko.commerceshop.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
