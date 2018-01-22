package com.github.marcopollivier.avenuecode.productmanager.app.controller.adapter;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ProductDTO;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;

public class ProductAdapter {

    private ProductDTO productDTO;

    public ProductAdapter(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Product convertToEntity() {
        return productDTO.toEntity();
    }

}
