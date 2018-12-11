package com.serverlessquiz.imageservice.dto;

import com.serverlessquiz.imageservice.persistence.ImageEntity;

public class ImageDtoTransformer {

    public static ImageDto entityToDto(ImageEntity imageEntity) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(imageEntity.getId());
        imageDto.setLabel(imageEntity.getAllLabelsAsString());
        imageDto.setUrl(imageEntity.getUrl());
        return imageDto;
    }
}
