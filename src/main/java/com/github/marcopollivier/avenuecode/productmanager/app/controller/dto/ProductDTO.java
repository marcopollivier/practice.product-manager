package com.github.marcopollivier.avenuecode.productmanager.app.controller.dto;

import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductDTO {

    @XmlAttribute
    @ApiModelProperty(value = "Product name", example = "Printer")
    private String name;

    @XmlAttribute
    @ApiModelProperty(value = "Product description", example = "Deskjet Printer",position = 1)
    private String description;

    @XmlAttribute(name = "subProducts")
    @ApiModelProperty(value = "Sub products", position = 2)
    private Set<ProductDTO> subProducts;

    @XmlAttribute(name = "images")
    @ApiModelProperty(value = "Images", position = 3)
    private Set<ImageDTO> images;

    public ProductDTO() {
        images = new HashSet<>();
        subProducts = new HashSet<>();
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

    public Set<ProductDTO> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(Set<ProductDTO> subProducts) {
        this.subProducts = subProducts;
    }

    public Set<ImageDTO> getImages() {
        return images;
    }

    public void setImages(Set<ImageDTO> images) {
        this.images = images;
    }

    public void addImageDTO(ImageDTO imageDTO) {
        if(imageDTO != null) {
            this.images.add(imageDTO);
        }
    }

    public void addSubProductDTO(ProductDTO productDTO) {
        if(productDTO != null) {
            this.subProducts.add(productDTO);
        }
    }

    public Product toEntity() {
        Product entity = new Product();

        entity.setName(this.name);
        entity.setDescription(this.description);

        subProducts.forEach(dto -> entity.addSubProduct(dto.toEntity()));

        images.forEach(dto -> entity.addImage(dto.toEntity()));

        return entity;
    }

}
