package com.isep.acme.model;

import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.isep.acme.dtos.ImageDTO;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ProdImage {

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Lob
    private Resource image;

    public ProdImage(Product product, Resource image) {
        setProduct(product);
        // addImage(image);;

    }

    private void setProduct(Product product) {
    }

    public ImageDTO toDto() {
        return new ImageDTO(this.id, product.getProductID());
    }

    /*
     * public ImageService addImage( Resource image){
     * 
     * return new AddImage (this.image);
     * }
     */
}
