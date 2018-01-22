package com.github.marcopollivier.avenuecode.productmanager.app.controller.adapter;

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

        return new ProductDTO().fromEntity(this.product);
    }

    public List<ProductDTO> fromEntityList() {
        List<ProductDTO> result = new ArrayList<>();

        for (Product prod : products) {
            result.add(new ProductDTO().fromEntity(prod));
        }

        return result;
    }

}
