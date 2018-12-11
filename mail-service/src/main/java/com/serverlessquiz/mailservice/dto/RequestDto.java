package com.serverlessquiz.mailservice.dto;

import java.util.List;
import java.util.Map;

public class RequestDto {

    private String receiver;
    private List<Map<String, Object>> highscores;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public List<Map<String, Object>> getHighscores() {
        return highscores;
    }

    public void setHighscores(List<Map<String, Object>> highscores) {
        this.highscores = highscores;
    }
}
