package com.isep.acme.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.isep.acme.mapper.ProductMapper;
import com.isep.acme.mapper.ProductMessage;
import com.isep.acme.model.Product;

@Component
public class ProductProducer {
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    private final RabbitTemplate rabbitTemplate;
    private final ProductMapper productMapper;

    @Autowired
    public ProductProducer(RabbitTemplate rabbitTemplate, ProductMapper productMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.productMapper = productMapper;
    }

    public void sendCreatedProductMessage(Product product) {
        ProductMessage message = productMapper.toMessage(product);
        rabbitTemplate.convertAndSend("ms.products.product-created", "", message);
    }

    public void sendUpdatedProductMessage(Product product) {
        ProductMessage message = productMapper.toMessage(product);
        rabbitTemplate.convertAndSend("ms.products.product-updated", "", message);
    }

    public void sendDeletedProductMessage(String productSku) {
        rabbitTemplate.convertAndSend("ms.products.product-deleted", "", productSku);
    }

    public Object sendRpcMessage(String exchange){
        return rabbitTemplate.convertSendAndReceive(exchange, "", applicationName);
    }
}
