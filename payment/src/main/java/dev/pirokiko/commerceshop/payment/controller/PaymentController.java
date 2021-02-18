package dev.pirokiko.commerceshop.payment.controller;

import dev.pirokiko.commerceshop.payment.dto.CreatePaymentDto;
import dev.pirokiko.commerceshop.payment.dto.PaymentDto;
import dev.pirokiko.commerceshop.payment.saga.CreatePaymentSaga;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final CreatePaymentSaga createPaymentSaga;

    public PaymentController(CreatePaymentSaga createPaymentSaga) {
        this.createPaymentSaga = createPaymentSaga;
    }

    @PostMapping("")
    public PaymentDto createPayment(@RequestBody CreatePaymentDto createPaymentDto){
        return createPaymentSaga.createPayment(createPaymentDto);
    }
}
