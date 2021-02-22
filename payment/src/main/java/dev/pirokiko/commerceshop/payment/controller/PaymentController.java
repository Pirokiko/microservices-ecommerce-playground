package dev.pirokiko.commerceshop.payment.controller;

import dev.pirokiko.commerceshop.payment.dto.CreatePaymentDto;
import dev.pirokiko.commerceshop.payment.dto.PaymentDto;
import dev.pirokiko.commerceshop.payment.saga.CreatePaymentSaga;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final CreatePaymentSaga createPaymentSaga;

    public PaymentController(CreatePaymentSaga createPaymentSaga) {
        this.createPaymentSaga = createPaymentSaga;
    }

    @PostMapping("")
    public PaymentDto createPayment(@RequestBody @Valid CreatePaymentDto createPaymentDto) {
        return createPaymentSaga.createPayment(createPaymentDto);
    }
}
