package com.github.marcopollivier.avenuecode.productmanager.app.controller.dto;

import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

public class ProductDTOTest {

    @Test
    public void toEntity() throws Exception {
        ProductDTO child1 = buildChildProduct();
        child1.addImageDTO(buildImage("child1"));

        ProductDTO child2 = buildChildProduct();
        child2.addImageDTO(buildImage("child2"));

        ProductDTO target = buildTargetProduct();
        target.addImageDTO(buildImage("Target"));
        target.addImageDTO(buildImage("Target"));
        target.addImageDTO(buildImage("Target"));
        target.addSubProductDTO(child1);
        target.addSubProductDTO(child2);

        Product product = target.toEntity();

        Assert.assertEquals("Target nonon", product.getName());
        Assert.assertEquals("Target nonon onono nono", product.getDescription());

        Assert.assertEquals(3, product.getImages().size());
        Assert.assertEquals(2, product.getSubProducts().size());
    }

    private ProductDTO buildTargetProduct() {
        ProductDTO dto = new ProductDTO();

        dto.setName("Target nonon");
        dto.setDescription("Target nonon onono nono");

        return dto;
    }

    private ProductDTO buildChildProduct() {
        ProductDTO dto = new ProductDTO();

        dto.setName("Child nonon");
        dto.setDescription(String.format("Child %s", RandomStringUtils.randomAlphabetic(15)));

        return dto;
    }

    private ImageDTO buildImage(String level) {
        ImageDTO dto = new ImageDTO();
        dto.setType(String.format("%s Image %s", level, RandomStringUtils.randomAlphabetic(10)));

        return dto;
    }


}