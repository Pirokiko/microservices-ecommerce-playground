package dev.pirokiko.commerceshop.order.saga.verifier;

import dev.pirokiko.commerceshop.order.api.CustomerServiceApi;
import dev.pirokiko.commerceshop.order.api.InventoryServiceApi;
import dev.pirokiko.commerceshop.order.dto.CustomerDto;
import dev.pirokiko.commerceshop.order.dto.OrderVerificationDto;
import dev.pirokiko.commerceshop.order.dto.ProductDto;
import dev.pirokiko.commerceshop.order.entity.Order;
import dev.pirokiko.commerceshop.order.entity.OrderItem;
import dev.pirokiko.commerceshop.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class OrderCreationVerifier {
    private final OrderRepository orderRepository;
    private final InventoryServiceApi inventoryApi;
    private final CustomerServiceApi customerApi;

    public OrderCreationVerifier(OrderRepository orderRepository, InventoryServiceApi inventoryApi, CustomerServiceApi customerApi) {
        this.orderRepository = orderRepository;
        this.inventoryApi = inventoryApi;
        this.customerApi = customerApi;
    }

    public CompletableFuture<@NotNull OrderVerificationDto> verifyAsync(final Long orderId) {
        return CompletableFuture.completedFuture(this.verify(orderId));
    }

    public @NotNull OrderVerificationDto verify(final Long orderId) {
        Order order = orderRepository.findByIdWithItems(orderId).orElseThrow();

        boolean itemsVerified = checkItems(order);
        boolean customerVerified = checkCustomer(order);
        boolean verified = itemsVerified && customerVerified;

        return new OrderVerificationDto(order, verified);
    }

    // This is where Spring Cloud Contract comes into play
    private boolean checkItems(final Order order) {
        order.getItems().forEach(item -> {
            try {
                ProductDto productDto = inventoryApi.getProduct(item.getProductId());
                item.setVerified(!item.getProductCost().equals(productDto.getCost()));
            } catch (RestClientException e) {
                log.error("api call failed: ", e);
                item.setVerified(false);
            }
        });

        // Save changes
        orderRepository.flush();

        return order.getItems().stream().map(OrderItem::getVerified).reduce(true, Boolean::logicalAnd);
    }

    // This is where Spring Cloud Contract comes into play
    private boolean checkCustomer(final Order order) {
        try {
            CustomerDto customerDto = customerApi.getCustomer(order.getCustomerId());
            return customerDto != null && customerDto.getId().equals(order.getCustomerId());
        } catch (RestClientException e) {
            return false;
        }
    }
}
