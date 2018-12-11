package com.serverlessquiz.mailservice.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.serverlessquiz.mailservice.dto.RequestDto;

import java.util.Map;

public class MailService {

    public String sendMailWithHighscores(RequestDto input) {

        final String FROM = System.getenv("MAIL_ADDRESS");
        final String TO = System.getenv("MAIL_ADDRESS");
        final String HTML_BODY = createHtmlBodyFromHighscores(input);
        final String TEXT_BODY = createTextBodyFromHighscores(input);
        final String SUBJECT = "Highscores Serverless Quiz";

        AmazonSimpleEmailService client =
                AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                        new Destination().withToAddresses(TO))
                .withMessage(new Message()
                        .withBody(new Body()
                                .withHtml(new Content()
                                        .withCharset("UTF-8").withData(HTML_BODY))
                                .withText(new Content()
                                        .withCharset("UTF-8").withData(TEXT_BODY)))
                        .withSubject(new Content()
                                .withCharset("UTF-8").withData(SUBJECT)))
                .withSource(FROM);
        client.sendEmail(request);
        return TEXT_BODY;
    }

    private String createTextBodyFromHighscores(RequestDto input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, Object> score: input.getHighscores()) {
            stringBuilder.append(score.get("name").toString());
            stringBuilder.append(": ");
            stringBuilder.append(score.get("score").toString());
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    private String createHtmlBodyFromHighscores(RequestDto input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, Object> score: input.getHighscores()) {
            stringBuilder.append(score.get("name").toString());
            stringBuilder.append(": ");
            stringBuilder.append(score.get("score").toString());
            stringBuilder.append("<br/>");
        }
        return stringBuilder.toString();
    }
}
