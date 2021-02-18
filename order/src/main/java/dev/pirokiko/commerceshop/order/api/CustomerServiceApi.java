package dev.pirokiko.commerceshop.order.api;

import dev.pirokiko.commerceshop.order.dto.CustomerDto;
import dev.pirokiko.commerceshop.order.dto.ProductDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerServiceApi {
    private final RestTemplate customerClient;

    public CustomerServiceApi(@Qualifier("customer") RestTemplate customerClient) {
        this.customerClient = customerClient;
    }

    public CustomerDto getCustomer(Long customerId) throws RestClientException {
        return customerClient.getForObject("/customer/" + customerId, CustomerDto.class);
    }
}
