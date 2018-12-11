package com.serverlessquiz.persistentimagequestionservice.dto;

import java.util.List;

public class QuestionDto {

    private String imageUrl;
    private List<String> labels;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
