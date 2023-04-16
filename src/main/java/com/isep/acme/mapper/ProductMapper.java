package com.isep.acme.mapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.model.Product;

@Component
public class ProductMapper {

    ObjectMapper objectMapper = new ObjectMapper();

    public Product toEntity(String body) {
        try {
            JsonNode json = new ObjectMapper().readTree(body);
            String sku = json.get("sku").asText();
            String description = json.get("description").asText();
            String designation = json.get("designation").asText();

            Product product = new Product();
            product.setSku(sku);
            product.setDescription(description);
            product.setDesignation(designation);

            return product;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> toMessageList(String jsonMessages) throws JsonProcessingException {
        Map<String, List<Product>> products = new ObjectMapper()
                .readValue(jsonMessages, new TypeReference<Map<String, List<Product>>>() {
                });
        return products.get("products");
    }

    public ProductMessage toMessage(Product product) {
        return new ProductMessage(product.getSku(),
                product.getDesignation(),
                product.getDescription());
    }

}
