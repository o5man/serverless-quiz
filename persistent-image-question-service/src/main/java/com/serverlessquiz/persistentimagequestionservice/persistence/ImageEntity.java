package com.serverlessquiz.persistentimagequestionservice.persistence;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName="Quiz_Images")
public class ImageEntity {

    private String id;
    private Long timestamp;
    private String filename;
    private List<String> labels;
    private String url;

    @DynamoDBHashKey(attributeName="Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAllLabelsAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < labels.size() && i < 3; i++) {
            stringBuilder.append(labels.get(i));
            if (i < labels.size() - 1 && i <  2) {
                stringBuilder.append(" - ");
            }
        }
        return stringBuilder.toString();
    }
}