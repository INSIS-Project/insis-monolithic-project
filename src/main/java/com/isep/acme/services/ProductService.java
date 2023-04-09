package com.isep.acme.services;

import com.isep.acme.dtos.ProductDTO;
import com.isep.acme.model.Product;

public interface ProductService {

    ProductDTO create(final Product manager);

    ProductDTO updateBySku(final String sku, final Product product);

    void deleteBySku(final String sku);
}
