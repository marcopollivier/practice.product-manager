package com.github.marcopollivier.avenuecode.productmanager.app.service;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.ProductRetrieveType;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.repository.ImageRepository;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.github.marcopollivier.avenuecode.productmanager.app.controller.ProductRetrieveType.*;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Product saveOrUpdate(Long productId, Product product) {
        Optional<Product> findById = productRepository.findByIdAndParentProductIsNull(productId);

        if (!findById.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity id %s not found", product.toString()));
        }

        Product updatable = findById.get();

        updatable.setName(product.getName());
        updatable.setDescription(product.getDescription());
        updatable.setParentProduct(product.getParentProduct());

        return productRepository.save(updatable);
    }

    public void delete(Long productId) {
        productRepository.delete(productId);
    }

    //FIXME technical debit. Necessary because Spring Boot is ignoring Lazy mode on relationship annotation
    public List<Product> retrieveProducts(ProductRetrieveType type) {
        List<Product> allByParentProductIsNull = productRepository.findAllByParentProductIsNull();

        if(type.equals(NO_RELATIONSHIP)) {
            //log
            allByParentProductIsNull.forEach(product -> product.setImages(null));
            allByParentProductIsNull.forEach(product -> product.setSubProducts(null));

        } else if(type.equals(ONLY_CHILD_PRODUCT)) {
            //log
            allByParentProductIsNull.forEach(product -> product.setImages(null));

        } else if(type.equals(ONLY_IMAGES)) {
            //log
            allByParentProductIsNull.forEach(product -> product.setSubProducts(null));

        } else if(type.equals(FULL_RELATIONSHIP)) {
            //log
        }

        return allByParentProductIsNull;
    }

    //FIXME technical debit. Necessary because Spring Boot is ignoring Lazy mode on relationship annotation
    public Product retrieveProduct(ProductRetrieveType type, Long productId) {
        Optional<Product> findById = productRepository.findByIdAndParentProductIsNull(productId);

        if (!findById.isPresent()) {
            return null;
        }

        Product product = findById.get();

        if(type.equals(NO_RELATIONSHIP)) {
            //log
            product.setSubProducts(null);
            product.setImages(null);

        } else if(type.equals(ONLY_CHILD_PRODUCT)) {
            //log
            product.setImages(null);

        } else if(type.equals(ONLY_IMAGES)) {
            //log
            product.setSubProducts(null);

        } else if(type.equals(FULL_RELATIONSHIP)) {
            //log
        }

        return product;
    }
}
