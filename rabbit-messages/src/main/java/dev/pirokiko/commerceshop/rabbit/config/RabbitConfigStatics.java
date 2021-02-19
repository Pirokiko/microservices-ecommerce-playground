package dev.pirokiko.commerceshop.rabbit.config;

public class RabbitConfigStatics {
    public static class Exchanges {
        public static final String EXCHANGE_NAME = "direct.exchange";
    }

    public static class Queues {
        public static final String ORDER_QUEUE_NAME = "order.queue";
        public static final String CUSTOMER_QUEUE_NAME = "customer.queue";
        public static final String INVENTORY_QUEUE_NAME = "inventory.queue";
        public static final String PAYMENT_QUEUE_NAME = "payment.queue";
    }

    public static class RoutingKeys {
        public static final String VERIFY_ORDER_CUSTOMER_REQUEST = "verify.order.customer.request";
        public static final String VERIFY_ORDER_CUSTOMER_RESPONSE = "verify.order.customer.response";

        public static final String VERIFY_ORDER_ITEMS_REQUEST = "verify.order.items.request";
        public static final String VERIFY_ORDER_ITEMS_RESPONSE = "verify.order.items.response";

        public static final String NEW_ORDER_VERIFIED = "order.new.verified";

        public static final String PAYMENT_CREATED = "payment.created";
    }




}
