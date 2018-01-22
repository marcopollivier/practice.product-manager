package com.github.marcopollivier.avenuecode.productmanager.app.controller.dto;

import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Image;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageDTOTest {

    @Test
    public void toEntity() throws Exception {
        ImageDTO dto = new ImageDTO();
        dto.setType("nonooon nonon");

        Image image = dto.toEntity();

        Assert.assertEquals("nonooon nonon", image.getType());
    }

    @Test
    public void toEntityWithNullType() throws Exception {
        ImageDTO dto = new ImageDTO();

        Image image = dto.toEntity();

        Assert.assertNotNull(image);
        Assert.assertNull(image.getType());
    }

}
