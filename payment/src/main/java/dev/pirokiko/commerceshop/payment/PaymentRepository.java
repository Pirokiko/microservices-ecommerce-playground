package dev.pirokiko.commerceshop.payment;

import dev.pirokiko.commerceshop.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}