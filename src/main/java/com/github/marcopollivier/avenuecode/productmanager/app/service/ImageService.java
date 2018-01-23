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

import static com.github.marcopollivier.avenuecode.productmanager.app.controller.ProductRetrieveType.NO_RELATIONSHIP;

@Service
@Transactional
public class ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductService productService;

    public Image add(Long productID, Image image) {
        Product product = productService.retrieveProduct(NO_RELATIONSHIP, productID);

        if(product == null) {
            throw new EntityNotFoundException();
        }

        image.setProduct(product);

        LOGGER.info("Saving...");
        return imageRepository.save(image);
    }

    public Image saveOrUpdate(Long imageId, Image image) {
        Optional<Image> findById = imageRepository.findById(imageId);

        if (!findById.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity id %s not found", image.toString()));
        }

        Image updatable = findById.get();

        updatable.setType(image.getType());

        LOGGER.info("Updating...");

        return imageRepository.save(updatable);
    }

    public void delete(Long imageId) {
        LOGGER.info("Deleting...");
        imageRepository.delete(imageId);
    }

    public List<Image> retrieveImages(Long parentProductId) {
        List<Image> listImages = imageRepository.findByProductId(parentProductId);

        if (listImages == null) {
            throw new EntityNotFoundException();
        }

        return listImages;
    }

}
