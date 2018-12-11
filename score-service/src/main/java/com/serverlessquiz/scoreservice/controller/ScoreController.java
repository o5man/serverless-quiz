package com.serverlessquiz.scoreservice.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverlessquiz.scoreservice.dto.ResponseDto;
import com.serverlessquiz.scoreservice.service.ScoreService;

public class ScoreController implements RequestHandler<Object, ResponseDto> {

    private ScoreService scoreService = new ScoreService();

    public ResponseDto handleRequest(Object input, Context context) {
        return scoreService.getTopTenScores();
    }
}
