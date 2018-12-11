package com.serverlessquiz.persistentimagequestionservice.dto;

public class ResponseDto {

    private QuestionDto nextQuestion;
    private Boolean lastAnswerCorrect;

    public QuestionDto getNextQuestion() {
        return nextQuestion;
    }

    public void setNextQuestion(QuestionDto nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    public Boolean getLastAnswerCorrect() {
        return lastAnswerCorrect;
    }

    public void setLastAnswerCorrect(Boolean lastAnswerCorrect) {
        this.lastAnswerCorrect = lastAnswerCorrect;
    }
}
