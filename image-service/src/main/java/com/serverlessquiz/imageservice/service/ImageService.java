package com.serverlessquiz.imageservice.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.serverlessquiz.imageservice.dto.ImageDto;
import com.serverlessquiz.imageservice.dto.ImageDtoTransformer;
import com.serverlessquiz.imageservice.persistence.ImageEntity;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class ImageService {

    private AmazonS3 s3client = AmazonS3ClientBuilder.standard().build();

    public void saveBase64ImageToS3(String image) {
        String base64Image = image.split(",")[1];
        byte[] data = DatatypeConverter.parseBase64Binary(base64Image);
        InputStream fis = new ByteArrayInputStream(data);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(data.length);
        metadata.setContentType("image/jpeg");
        metadata.setCacheControl("public, max-age=31536000");
        s3client.putObject(new PutObjectRequest("serverless-workshop-images",
                String.valueOf(System.currentTimeMillis()) + ".jpg", fis, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public List<ImageDto> getAllImages() {
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

        PaginatedScanList<ImageEntity> imageEntities = mapper.scan(ImageEntity.class, new DynamoDBScanExpression());
        List<ImageDto> imageDtos =  imageEntities.stream()
                .map(ImageDtoTransformer::entityToDto)
                .collect(Collectors.toList());
        return imageDtos;
    }
}
