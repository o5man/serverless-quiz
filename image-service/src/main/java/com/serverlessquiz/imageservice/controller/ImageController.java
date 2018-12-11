package com.serverlessquiz.imageservice.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.serverlessquiz.imageservice.dto.ImageDto;
import com.serverlessquiz.imageservice.dto.RequestDto;
import com.serverlessquiz.imageservice.service.ImageService;

import java.util.List;

public class ImageController {

    private ImageService imageService = new ImageService();

    public String postImage(RequestDto input, Context context) {
        imageService.saveBase64ImageToS3(input.getBase64Image());
        return "Image was saved to S3.";
    }

    public List<ImageDto> getUploadedImages (Object input, Context context) {
        return imageService.getAllImages();
    }
}
