package dev.pirokiko.commerceshop.customer.config.amqp;

import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {
    private static final String EXCHANGE_NAME = RabbitConfigStatics.Exchanges.EXCHANGE_NAME;

    private static final String QUEUE_NAME = RabbitConfigStatics.Queues.CUSTOMER_QUEUE_NAME;

    private static Binding getCustomerQueueBinding(String routingKey) {
        return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, routingKey, null);
    }

    @Bean
    public Exchange fanoutExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue customerQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding verifyCustomerRequestBinding() {
        return getCustomerQueueBinding(RabbitConfigStatics.RoutingKeys.VERIFY_ORDER_CUSTOMER_REQUEST);
    }

    // Enforce a connection to be created, even if "somehow" configuration alone doesn't require it
    @Bean
    ApplicationRunner runner(ConnectionFactory cf) {
        return args -> cf.createConnection().close();
    }
}
