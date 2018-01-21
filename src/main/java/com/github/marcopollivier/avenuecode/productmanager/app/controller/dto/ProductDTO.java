package com.github.marcopollivier.avenuecode.productmanager.app.controller.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.HashSet;
import java.util.Set;

@XmlAccessorType(value = XmlAccessType.NONE)
public class ProductDTO {

    @XmlAttribute
    @ApiModelProperty(value = "Product name", required = true, position = 0)
    private String name;

    @XmlAttribute
    @ApiModelProperty(value = "Product description", required = true, position = 0)
    private String description;

    @XmlAttribute
    @ApiModelProperty(value = "Parent product", required = true, position = 0)
    private ProductDTO parentProduct;

    @XmlAttribute
    @ApiModelProperty(value = "Sub products", required = true, position = 0)
    private Set<ProductDTO> subProducts;

    @XmlAttribute
    @ApiModelProperty(value = "Images", required = true, position = 0)
    private Set<ImageDTO> images;

    public ProductDTO() {
        images = new HashSet<>();
        subProducts = new HashSet<>();
        parentProduct = new ProductDTO();
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

    public ProductDTO getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(ProductDTO parentProduct) {
        this.parentProduct = parentProduct;
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

}
