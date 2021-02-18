package dev.pirokiko.commerceshop.payment.service;

import dev.pirokiko.commerceshop.payment.PaymentRepository;
import dev.pirokiko.commerceshop.payment.dto.CreatePaymentDto;
import dev.pirokiko.commerceshop.payment.entity.Payment;
import dev.pirokiko.commerceshop.payment.enums.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class CreatePaymentService {
    private final PaymentRepository paymentRepository;

    public CreatePaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(CreatePaymentDto createPaymentDto) {
        Payment payment = new Payment();
        payment.setAmount(createPaymentDto.getAmount());
        payment.setAmountPayed(0.0);
        payment.setStatus(PaymentStatus.PAYMENT_STARTED);

        return paymentRepository.save(payment);
    }
}
