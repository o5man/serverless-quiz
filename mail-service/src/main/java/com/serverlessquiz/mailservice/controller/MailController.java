package com.serverlessquiz.mailservice.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverlessquiz.mailservice.dto.RequestDto;
import com.serverlessquiz.mailservice.service.MailService;

public class MailController implements RequestHandler<RequestDto, String> {

    private MailService mailService = new MailService();

    @Override
    public String handleRequest(RequestDto input, Context context) {
        return mailService.sendMailWithHighscores(input);
    }
}
