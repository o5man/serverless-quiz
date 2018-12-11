package com.serverlessquiz.persistentquestionservice.dto;

public class RequestDto {

    private String playerName;
    private String playerId;
    private String playerColor;
    private String askedQuestion;
    private String givenAnswer;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public String getAskedQuestion() {
        return askedQuestion;
    }

    public void setAskedQuestion(String askedQuestion) {
        this.askedQuestion = askedQuestion;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }
}
