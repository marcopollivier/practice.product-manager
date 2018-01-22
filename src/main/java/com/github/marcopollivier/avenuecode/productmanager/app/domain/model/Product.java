package com.github.marcopollivier.avenuecode.productmanager.app.domain.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_product_id")
    private Product parentProduct;

    @OneToMany(
            mappedBy = "parentProduct",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Product> subProducts;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Image> images;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Product() {
        subProducts = new HashSet<>();
        images = new HashSet<>();
    }

    //
    @PreUpdate
    public void onPreUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }

    @PrePersist
    public void onPrePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.setUpdatedAt(now);
        this.setCreatedAt(now);
    }

    //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Product getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(Product parentProduct) {
        this.parentProduct = parentProduct;
    }

    public Set<Product> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(Set<Product> subProducts) {
        this.subProducts = subProducts;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    //
    public void addSubProduct(Product product) {
        if(product != null) {
            this.subProducts.add(product);
            product.setParentProduct(this);
        }
    }

    public void addImage(Image image) {
        if(image != null) {
            this.images.add(image);
            image.setProduct(this);
        }
    }

}
