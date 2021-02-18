package dev.pirokiko.commerceshop.payment.saga;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pirokiko.commerceshop.payment.dto.CreatePaymentDto;
import dev.pirokiko.commerceshop.payment.dto.PaymentDto;
import dev.pirokiko.commerceshop.payment.entity.Payment;
import dev.pirokiko.commerceshop.payment.service.CreatePaymentService;
import org.springframework.stereotype.Service;

@Service
public class CreatePaymentSaga {
    private final CreatePaymentService createPaymentService;
    private final ObjectMapper objectMapper;

    public CreatePaymentSaga(CreatePaymentService createPaymentService, ObjectMapper objectMapper) {
        this.createPaymentService = createPaymentService;
        this.objectMapper = objectMapper;
    }

    public PaymentDto createPayment(CreatePaymentDto createPaymentDto){
        Payment payment = createPaymentService.createPayment(createPaymentDto);

        return objectMapper.convertValue(payment, PaymentDto.class);
    }
}
