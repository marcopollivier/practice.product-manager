package com.github.marcopollivier.avenuecode.productmanager.app.domain.repository;

import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

//    @Query("select i from Image i join Product p where p.id = ?1")
//    List<Image> findAllByProduct(Long productId);

}
