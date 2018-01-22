package com.github.marcopollivier.avenuecode.productmanager.app.service;

import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.repository.ImageRepository;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public List<Product> retrieveAllSingleProducts() {
        return productRepository.findAllByParentProductIsNull();
    }

    public Product retrieveASingleProduct(Long productId) {
        Optional<Product> findById = productRepository.findByIdAndParentProductIsNull(productId);

        if (findById.isPresent()) {
            return findById.get();
        }

        return null;
    }

}
