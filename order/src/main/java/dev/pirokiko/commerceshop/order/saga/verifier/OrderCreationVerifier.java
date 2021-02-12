package dev.pirokiko.commerceshop.order.saga.verifier;

import dev.pirokiko.commerceshop.order.dto.CustomerDto;
import dev.pirokiko.commerceshop.order.dto.ProductDto;
import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.entity.OrderItem;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import dev.pirokiko.commerceshop.order.service.CustomerApiService;
import dev.pirokiko.commerceshop.order.service.InventoryApiService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

@Component
public class OrderCreationVerifier implements Verifier<Order> {
    private final OrderRepository orderRepository;
    private final InventoryApiService inventoryApi;
    private final CustomerApiService customerApi;

    public OrderCreationVerifier(OrderRepository orderRepository, InventoryApiService inventoryApi, CustomerApiService customerApi) {
        this.orderRepository = orderRepository;
        this.inventoryApi = inventoryApi;
        this.customerApi = customerApi;
    }

    @Override
    public CompletableFuture<@NotNull Boolean> verify(final Order order) {
        boolean itemsVerified = checkItems(order);
        boolean customerVerified = checkCustomer(order);

        return CompletableFuture.completedFuture(itemsVerified && customerVerified);
    }

    // This is where Spring Cloud Contract comes into play
    protected boolean checkItems(final Order order) {
        order.getItems().forEach(item -> {
            try {
                ProductDto productDto = inventoryApi.getProduct(item.getProductId());
                item.setVerified(!item.getProductCost().equals(productDto.getCost()));
            }catch (RestClientException e){
                item.setVerified(false);
            }
        });

        // Save changes
        orderRepository.flush();

        return order.getItems().stream().map(OrderItem::getVerified).reduce(true, Boolean::logicalAnd);
    }

    // This is where Spring Cloud Contract comes into play
    protected boolean checkCustomer(final Order order) {
        try {
            CustomerDto customerDto = customerApi.getCustomer(order.getCustomerId());
            return customerDto != null && customerDto.getId().equals(order.getCustomerId());
        } catch (RestClientException e){
            return false;
        }
    }
}
