package com.isep.acme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.dtos.ProductDTO;
import com.isep.acme.model.Product;
import com.isep.acme.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductDTO create(final Product product) {
        final Product p = new Product(product.getSku(),
                product.getDesignation(),
                product.getDescription());

        return repository.save(p).toDto();
    }

    @Override
    public ProductDTO updateBySku(String sku, Product product) {

        final Optional<Product> productToUpdate = repository.findBySku(sku);

        if (productToUpdate.isEmpty())
            return null;

        productToUpdate.get().updateProduct(product);

        Product productUpdated = repository.save(productToUpdate.get());

        return productUpdated.toDto();
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }

    public boolean existsBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);

        return product != null;
    }
}
