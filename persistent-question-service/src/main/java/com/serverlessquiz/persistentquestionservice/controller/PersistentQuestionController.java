package com.serverlessquiz.persistentquestionservice.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverlessquiz.persistentquestionservice.dto.QuestionDto;
import com.serverlessquiz.persistentquestionservice.dto.RequestDto;
import com.serverlessquiz.persistentquestionservice.dto.ResponseDto;
import com.serverlessquiz.persistentquestionservice.service.PersistentQuestionService;
import com.serverlessquiz.persistentquestionservice.service.ScoreService;

public class PersistentQuestionController implements RequestHandler<RequestDto, ResponseDto> {

    private PersistentQuestionService questionService = new PersistentQuestionService();
    private ScoreService scoreService = new ScoreService();

    @Override
    public ResponseDto handleRequest(RequestDto input, Context context) {

        ResponseDto responseDto = new ResponseDto();
        QuestionDto nextQuestion = questionService.getQuestion();
        responseDto.setNextQuestion(nextQuestion);
        if ((null != input.getAskedQuestion()) && (null != input.getGivenAnswer())) {
            boolean lastAnswerCorrect = questionService.getAnswerCorrectness(input.getAskedQuestion(), input.getGivenAnswer());
            if (lastAnswerCorrect) {
                scoreService.increaseScoreOfPlayer(input.getPlayerName(), input.getPlayerId(), input.getPlayerColor());
            }
            responseDto.setLastAnswerCorrect(lastAnswerCorrect);
        }

        return responseDto;

    }
}
