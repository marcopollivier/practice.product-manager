package com.github.marcopollivier.avenuecode.productmanager.app.domain.repository;

import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Image;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByProductId(Long productId);

}
