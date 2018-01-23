package com.github.marcopollivier.avenuecode.productmanager.app.domain.repository;

import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.parentProduct is null")
    List<Product> findAllByParentProductIsNull();

    @Query("select p from Product p where p.parentProduct is null and p.id = ?1")
    Optional<Product> findByIdAndParentProductIsNull(Long productId);

}
