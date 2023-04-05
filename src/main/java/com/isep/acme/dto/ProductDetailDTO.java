package com.isep.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailDTO {
    private String sku;
    private String designation;
    private String description;
}
