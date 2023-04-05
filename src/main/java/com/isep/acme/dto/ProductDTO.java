package com.isep.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDTO {
    private String sku;
    private String designation;
}
