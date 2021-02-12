package dev.pirokiko.commerceshop.order.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderNumberGenerator {
    public String generateOrderNumer(){
        return UUID.randomUUID().toString();
    }
}
