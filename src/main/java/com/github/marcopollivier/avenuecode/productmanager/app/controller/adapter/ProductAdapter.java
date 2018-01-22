package com.github.marcopollivier.avenuecode.productmanager.app.controller.adapter;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ImageDTO;
import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ProductDTO;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter {

    private ProductDTO productDTO;
    private Product product;
    private List<Product> products;


    public ProductAdapter(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public ProductAdapter(Product product) {
        this.product = product;
    }

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    public Product convertToEntity() {
        if(productDTO == null) {
            return null;
        }

        return productDTO.toEntity();
    }

    public ProductDTO convertToDTO() {
        if(product == null) {
            return null;
        }

        return fromEntity(this.product);
    }

    public List<ProductDTO> fromEntityList() {
        if(products == null || products.isEmpty()) {
            return null;
        }
        List<ProductDTO> result = new ArrayList<>();

        products.forEach(prod -> result.add(fromEntity(prod)));

        return result;
    }


    private ProductDTO fromEntity(Product product) {
        ProductDTO dto = new ProductDTO();

        dto.setName(product.getName());
        dto.setDescription(product.getDescription());

        product.getSubProducts().forEach(subProduct -> dto.addSubProductDTO(fromEntity(subProduct)));

        product.getImages().forEach(imageEntity -> dto.addImageDTO(new ImageDTO(imageEntity.getType())));

        return dto;
    }

}
