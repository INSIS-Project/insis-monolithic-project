package com.isep.acme.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class ProductDetailDTO {
    private String sku;
    private String designation;
    private String description;
}
