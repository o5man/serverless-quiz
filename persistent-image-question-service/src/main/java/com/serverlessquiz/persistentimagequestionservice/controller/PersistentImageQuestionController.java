package com.serverlessquiz.persistentimagequestionservice.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverlessquiz.persistentimagequestionservice.dto.RequestDto;
import com.serverlessquiz.persistentimagequestionservice.dto.ResponseDto;
import com.serverlessquiz.persistentimagequestionservice.service.PersistentImageQuestionService;

public class PersistentImageQuestionController implements RequestHandler<RequestDto, ResponseDto> {

    private PersistentImageQuestionService imageQuestionService = new PersistentImageQuestionService();

    @Override
    public ResponseDto handleRequest(RequestDto input, Context context) {
        return imageQuestionService.getNextQuestion(input);
    }

}
