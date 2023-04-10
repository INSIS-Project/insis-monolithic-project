package com.isep.acme.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue productCreatedQueue() {
        return new Queue("products.create-product");
    }

    @Bean
    public Queue productUpdatedQueue() {
        return new Queue("products.update-product");
    }

    @Bean
    public Queue productDeletedQueue() {
        return new Queue("products.delete-product");
    }

    @Bean
    public FanoutExchange productCreatedExchange() {
        return new FanoutExchange("ms.products.product-created");
    }

    @Bean
    public FanoutExchange productUpdatedExchange() {
        return new FanoutExchange("ms.products.product-updated");
    }

    @Bean
    public FanoutExchange productDeletedExchange() {
        return new FanoutExchange("ms.products.product-deleted");
    }

    @Bean
    public Binding productCreatedBinding() {
        Queue productCreatedQueue = new Queue("products.create-product");
        FanoutExchange productCreatedExchange = new FanoutExchange("ms.products.product-created");
        return BindingBuilder.bind(productCreatedQueue).to(productCreatedExchange);
    }

    @Bean
    public Binding productUpdatedBinding() {
        Queue productUpdatedQueue = new Queue("products.update-product");
        FanoutExchange productUpdatedExchange = new FanoutExchange("ms.products.product-updated");
        return BindingBuilder.bind(productUpdatedQueue).to(productUpdatedExchange);
    }

    @Bean
    public Binding productDeletedBinding() {
        Queue productDeletedQueue = new Queue("products.delete-product");
        FanoutExchange productDeletedExchange = new FanoutExchange("ms.products.product-deleted");
        return BindingBuilder.bind(productDeletedQueue).to(productDeletedExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationListener(RabbitAdmin rabbitAdmin) {
        return event -> {
            rabbitAdmin.initialize();
        };
    }
}