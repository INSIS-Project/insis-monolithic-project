package com.isep.acme.dtos;

import java.util.UUID;

import com.isep.acme.services.ImageService;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImageDTO {

    private ImageService service;
    private UUID id;
    private UUID productID;

    public ImageDTO(UUID id, UUID productID) {
        this.id = id;
        this.productID = productID;
    }

    public Iterable<ImageDTO> getImageProduct() {
        return service.getImageProduct();
    }
}
