package com.github.marcopollivier.avenuecode.productmanager.app.service;

import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.repository.ImageRepository;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public void delete(Long productId) {
        productRepository.delete(productId);
    }

}
