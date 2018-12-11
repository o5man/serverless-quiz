package com.serverlessquiz.persistentquestionservice.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.serverlessquiz.persistentquestionservice.persistence.ScoreEntity;

public class ScoreService {

    public void increaseScoreOfPlayer(String playerName, String playerId, String playerColor) {

        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

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
