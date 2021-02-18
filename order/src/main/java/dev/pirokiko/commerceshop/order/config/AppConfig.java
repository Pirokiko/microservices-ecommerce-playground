package dev.pirokiko.commerceshop.order.config;

import org.openapitools.client.api.PaymentControllerApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAsync()
public class AppConfig {

    @Qualifier("inventory")
    @Bean
    public RestTemplate getInventoryRestTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.rootUri("http://localhost:9002").build();
    }

    @Qualifier("payment")
    @Bean
    public PaymentControllerApi getPaymentControllerApi(){
        PaymentControllerApi api = new PaymentControllerApi();
        System.out.println(api.getApiClient().getBasePath());
        return api;
    }

    @Qualifier("customer")
    @Bean
    public RestTemplate getCustomerRestTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.rootUri("http://localhost:9001").build();
    }
}
