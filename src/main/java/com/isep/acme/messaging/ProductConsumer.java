package com.isep.acme.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.model.Product;
import com.isep.acme.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class ProductConsumer {
    // queue "products.create-product"
    // exchange "ms.products.product-created"

    private final ProductService productService;
    
    @RabbitListener(queues = "#{productCreatedQueue}")
    public void receiveCreatedProductMessage(Message message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(message.getBody(), Product.class);
            log.info("Received message for created product: {}", product.getSku());

            // Do something if the product already exists for the instance

            // Save the product in the database
            productService.create(product);
            log.info("Product created successfully with SKU: {}", product.getSku());

        } catch (Exception e) {
            log.error("Error receving message.", e.getMessage());
        }
    }

    @RabbitListener(queues = "#{productUpdatedQueue}")
    public void receiveUpdatedProductMessage(Product product) {
        try {
            log.info("Received message for updated product: {}", product);
            // Do something with the received product message
        } catch (Exception e) {
            log.error("Error receiving updated product message: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = "#{productDeletedQueue}")
    public void receiveDeletedProductMessage(Product product) {
        try {
            log.info("Received message for deleted product: {}", product);
            // Do something with the received product message
        } catch (Exception e) {
            log.error("Error receiving deleted product message: {}", e.getMessage());
        }
    }
}
