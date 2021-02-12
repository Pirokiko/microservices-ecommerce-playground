package dev.pirokiko.commerceshop.order.saga.verifier;

import org.springframework.scheduling.annotation.Async;

import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

public interface Verifier<T> {
    @Async
    public CompletableFuture<@NotNull Boolean> verify(T object);
}
