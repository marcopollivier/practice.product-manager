package com.github.marcopollivier.avenuecode.productmanager.app.service;

import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Image;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    private ImageRepository imageRepository;

//    public Product saveOrUpdate(Product product) {
//        LOGGER.info("Saving...");
//        return imageRepository.save(product);
//    }
//
//    public Product saveOrUpdate(Long productId, Product product) {
//        Optional<Product> findById = imageRepository.findByIdAndParentProductIsNull(productId);
//
//        if (!findById.isPresent()) {
//            throw new EntityNotFoundException(String.format("Entity id %s not found", product.toString()));
//        }
//
//        Product updatable = findById.get();
//
//        updatable.setName(product.getName());
//        updatable.setDescription(product.getDescription());
//        updatable.setParentProduct(product.getParentProduct());
//
//        LOGGER.info("Updating...");
//
//        return imageRepository.save(updatable);
//    }
//
//    public void delete(Long productId) {
//        LOGGER.info("Deleting...");
//        imageRepository.delete(productId);
//    }

    public List<Image> retrieveImages(Long parentProductId) {
        List<Image> listImages = imageRepository.findByProductId(parentProductId);

        if (listImages == null) {
            throw new EntityNotFoundException();
        }

        return listImages;
    }

}
