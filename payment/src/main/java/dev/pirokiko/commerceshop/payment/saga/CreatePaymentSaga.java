package dev.pirokiko.commerceshop.payment.saga;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pirokiko.commerceshop.payment.dto.CreatePaymentDto;
import dev.pirokiko.commerceshop.payment.dto.PaymentDto;
import dev.pirokiko.commerceshop.payment.entity.Payment;
import dev.pirokiko.commerceshop.payment.service.CreatePaymentService;
import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import dev.pirokiko.commerceshop.rabbit.message.PaymentCreatedMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreatePaymentSaga {
    private final CreatePaymentService createPaymentService;
    private final ObjectMapper objectMapper;
    private final AmqpTemplate amqpTemplate;

    public CreatePaymentSaga(CreatePaymentService createPaymentService, ObjectMapper objectMapper, AmqpTemplate amqpTemplate) {
        this.createPaymentService = createPaymentService;
        this.objectMapper = objectMapper;
        this.amqpTemplate = amqpTemplate;
    }

    public PaymentDto createPayment(CreatePaymentDto createPaymentDto) {
        Payment payment = createPaymentService.createPayment(createPaymentDto);

        amqpTemplate.convertAndSend(RabbitConfigStatics.RoutingKeys.PAYMENT_CREATED, objectMapper.convertValue(payment, PaymentCreatedMessage.class));

        return objectMapper.convertValue(payment, PaymentDto.class);
    }
}
