package com.serverlessquiz.simplequestionservice.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverlessquiz.simplequestionservice.dto.QuestionDto;
import com.serverlessquiz.simplequestionservice.dto.RequestDto;
import com.serverlessquiz.simplequestionservice.dto.ResponseDto;
import com.serverlessquiz.simplequestionservice.service.SimpleQuestionService;

public class SimpleQuestionController implements RequestHandler<RequestDto, ResponseDto> {

    private SimpleQuestionService questionService = new SimpleQuestionService();

    @Override
    public ResponseDto handleRequest(RequestDto input, Context context) {

        ResponseDto responseDto = new ResponseDto();
        QuestionDto nextQuestion = questionService.getQuestion();
        responseDto.setNextQuestion(nextQuestion);
        if ((null != input.getAskedQuestion()) && (null != input.getGivenAnswer())) {
            boolean lastAnswerCorrect = questionService.getAnswerCorrectness(input.getAskedQuestion(), input.getGivenAnswer());
            responseDto.setLastAnswerCorrect(lastAnswerCorrect);
        }
        return responseDto;

    }
}
