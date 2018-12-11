package com.serverlessquiz.persistentimagequestionservice.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.serverlessquiz.persistentimagequestionservice.dto.QuestionDto;
import com.serverlessquiz.persistentimagequestionservice.dto.RequestDto;
import com.serverlessquiz.persistentimagequestionservice.dto.ResponseDto;
import com.serverlessquiz.persistentimagequestionservice.persistence.ImageEntity;
import com.serverlessquiz.persistentimagequestionservice.persistence.ScoreEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersistentImageQuestionService {

    private AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard().build();
    private DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

    public ResponseDto getNextQuestion(RequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        QuestionDto questionDto = new QuestionDto();

        // Assess last answer and maybe increase score
        PaginatedScanList<ImageEntity> imageEntities = mapper.scan(ImageEntity.class, new DynamoDBScanExpression());
        if (requestDto.getAskedQuestion() != null && requestDto.getGivenAnswer() != null) {
            ImageEntity previouslyAskedImage = imageEntities.stream()
                    .filter(imageEntity -> imageEntity.getUrl().equals(requestDto.getAskedQuestion()))
                    .findFirst().get();
            if (previouslyAskedImage.getAllLabelsAsString().equals(requestDto.getGivenAnswer())) {
                increaseScoreOfPlayer(requestDto.getPlayerName(), requestDto.getPlayerId(), requestDto.getPlayerColor());
                responseDto.setLastAnswerCorrect(true);
            } else {
                responseDto.setLastAnswerCorrect(false);
            }
        }

        // Get next question
        Random random = new Random();
        ImageEntity nextAskedImage = imageEntities.get(random.nextInt(imageEntities.size()));
        while (requestDto.getAskedQuestion() != null && nextAskedImage.getUrl().equals(requestDto.getAskedQuestion())) {
            nextAskedImage = imageEntities.get(random.nextInt(imageEntities.size()));
        }
        List<String> possibleLabels = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String randomLabel = imageEntities.get(random.nextInt(imageEntities.size())).getAllLabelsAsString();
            while (possibleLabels.contains(randomLabel) || nextAskedImage.getAllLabelsAsString().equals(randomLabel)) {
                randomLabel = imageEntities.get(random.nextInt(imageEntities.size())).getAllLabelsAsString();
            }
            possibleLabels.add(randomLabel);
        }
        possibleLabels.add(random.nextInt(possibleLabels.size()), nextAskedImage.getAllLabelsAsString());
        possibleLabels.remove(possibleLabels.size() - 1);
        questionDto.setLabels(possibleLabels);
        questionDto.setImageUrl(nextAskedImage.getUrl());
        responseDto.setNextQuestion(questionDto);

        return responseDto;
    }

    private void increaseScoreOfPlayer(String playerName, String playerId, String playerColor) {
        ScoreEntity score = mapper.load(ScoreEntity.class, playerId);
        if (null == score) {
            score = new ScoreEntity();
            score.setPlayerName(playerName);
            score.setPlayerId(playerId);
            score.setPlayerColor(playerColor);
            score.setScore(1);
        } else {
            score.setScore(score.getScore() + 1);
        }
        mapper.save(score);

    }
}
