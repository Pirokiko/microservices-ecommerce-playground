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
    private final OrderCustomerVerifier orderCustomerVerifier;
    private final OrderItemsVerifier orderItemsVerifier;

    public OrderCreationVerifier(OrderRepository orderRepository, OrderCustomerVerifier orderCustomerVerifier, OrderItemsVerifier orderItemsVerifier) {
        this.orderRepository = orderRepository;
        this.orderCustomerVerifier = orderCustomerVerifier;
        this.orderItemsVerifier = orderItemsVerifier;
    }

    public CompletableFuture<@NotNull OrderVerificationDto> verifyAsync(final Long orderId) {
        CompletableFuture<Boolean> customerVerification = orderCustomerVerifier.verify(orderId);
        CompletableFuture<Boolean> itemsVerification = orderItemsVerifier.verify(orderId);
        return CompletableFuture.allOf(customerVerification, itemsVerification)
                .thenApply((empty) -> {
                    Order order = orderRepository.findById(orderId).orElseThrow();
                    Boolean customerVerified = customerVerification.join(); // Should not involve waiting as it should already be completed
                    Boolean itemsVerified = itemsVerification.join(); // Should not involve waiting as it should already be completed
                    Boolean verified = customerVerified && itemsVerified;
                    return new OrderVerificationDto(order, verified);
                });
    }
}
