package com.serverlessquiz.persistentquestionservice.dto;

import java.util.List;

public class QuestionDto {

    private String colorCode;
    private List<String> colorNames;

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public List<String> getColorNames() {
        return colorNames;
    }

    public void setColorNames(List<String> colorNames) {
        this.colorNames = colorNames;
    }
}
