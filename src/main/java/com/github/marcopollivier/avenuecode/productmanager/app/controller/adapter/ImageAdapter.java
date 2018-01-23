package com.github.marcopollivier.avenuecode.productmanager.app.controller.adapter;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ImageDTO;
import com.github.marcopollivier.avenuecode.productmanager.app.controller.dto.ProductDTO;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Image;
import com.github.marcopollivier.avenuecode.productmanager.app.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter {

    private List<Image> images;

    public ImageAdapter(List<Image> images) {
        this.images = images;
    }

    public List<ImageDTO> fromEntityList() {

        if(images == null || images.isEmpty()) {
            return null;
        }

        List<ImageDTO> result = new ArrayList<>();

        images.forEach(img -> result.add(new ImageDTO(img.getId(), img.getType())));

        return result;
    }

}
