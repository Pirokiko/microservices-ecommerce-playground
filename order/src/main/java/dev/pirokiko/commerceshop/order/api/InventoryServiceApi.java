package dev.pirokiko.commerceshop.order.api;

import dev.pirokiko.commerceshop.order.dto.ProductDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryServiceApi {
    private final RestTemplate inventoryClient;

    public InventoryServiceApi(@Qualifier("inventory") RestTemplate inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public ProductDto getProduct(Long productId) throws RestClientException {
        return inventoryClient.getForObject("/product/" + productId, ProductDto.class);
    }
}
