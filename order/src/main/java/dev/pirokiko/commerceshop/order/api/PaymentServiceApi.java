package dev.pirokiko.commerceshop.order.api;

import dev.pirokiko.commerceshop.order.entity.Order;
import org.openapitools.client.api.PaymentControllerApi;
import org.openapitools.client.model.CreatePaymentDto;
import org.openapitools.client.model.PaymentDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Component
public class PaymentServiceApi {
    private final PaymentControllerApi paymentClient;

    public PaymentServiceApi(PaymentControllerApi paymentClient){
        this.paymentClient = paymentClient;
    }

    public PaymentDto createPayment(Order order) throws RestClientException {
        CreatePaymentDto createPaymentDto = new CreatePaymentDto();
        return paymentClient.createPayment(createPaymentDto);
    }
}
