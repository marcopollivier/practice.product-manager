package com.github.marcopollivier.avenuecode.productmanager.app.service;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.ProductRetrieveType;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product saveOrUpdate(Product product) {
        LOGGER.info("Saving...");
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

        LOGGER.info("Updating...");

        return productRepository.save(updatable);
    }

    public void delete(Long productId) {
        LOGGER.info("Deleting...");
        productRepository.delete(productId);
    }

    //FIXME technical debit. Necessary because Spring Boot is ignoring Lazy mode on relationship annotation
    public List<Product> retrieveProducts(ProductRetrieveType type) {
        List<Product> allByParentProductIsNull = productRepository.findAllByParentProductIsNull();

        if(type.equals(NO_RELATIONSHIP)) {
            LOGGER.info("No Relationship");
            allByParentProductIsNull.forEach(product -> product.setImages(null));
            allByParentProductIsNull.forEach(product -> product.setSubProducts(null));

        } else if(type.equals(ONLY_CHILD_PRODUCT)) {
            LOGGER.info("Only child");
            allByParentProductIsNull.forEach(product -> product.setImages(null));

        } else if(type.equals(ONLY_IMAGES)) {
            LOGGER.info("Only images");
            allByParentProductIsNull.forEach(product -> product.setSubProducts(null));

        } else if(type.equals(FULL_RELATIONSHIP)) {
            LOGGER.info("Complete product");
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
            LOGGER.info("No Relationship");
            product.setSubProducts(null);
            product.setImages(null);

        } else if(type.equals(ONLY_CHILD_PRODUCT)) {
            LOGGER.info("Only child");
            product.setImages(null);

        } else if(type.equals(ONLY_IMAGES)) {
            LOGGER.info("Only images");
            product.setSubProducts(null);

        } else if(type.equals(FULL_RELATIONSHIP)) {
            LOGGER.info("Complete product");
        }

        return product;
    }


    public List<Product> retrieveChildProducts(Long parentProductId) {
        List<Product> listProducts = productRepository.findByParentProductId(parentProductId);

        if (listProducts == null) {
            throw new EntityNotFoundException();
        }

        return listProducts;
    }

}
