package dev.pirokiko.commerceshop.inventory.config.amqp;

import dev.pirokiko.commerceshop.rabbit.config.RabbitConfigStatics;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private static final String EXCHANGE_NAME = RabbitConfigStatics.Exchanges.EXCHANGE_NAME;

    private static final String QUEUE_NAME = RabbitConfigStatics.Queues.INVENTORY_QUEUE_NAME;

    private static Binding getInventoryQueueBinding(String routingKey) {
        return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, routingKey, null);
    }

    @Bean
    public Exchange fanoutExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding verifyItemsRequestBinding() {
        return getInventoryQueueBinding(RabbitConfigStatics.RoutingKeys.VERIFY_ORDER_ITEMS_REQUEST);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
