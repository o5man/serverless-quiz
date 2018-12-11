package com.serverlessquiz.scoreservice.service;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.serverlessquiz.scoreservice.dto.PlayerScoreDto;
import com.serverlessquiz.scoreservice.dto.ResponseDto;
import com.serverlessquiz.scoreservice.persistence.ScoreEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreService {

    public ResponseDto getTopTenScores() {

        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        ResponseDto result = new ResponseDto();
        result.setPlayers(new ArrayList<>());

        PaginatedScanList<ScoreEntity> scores = mapper.scan(ScoreEntity.class, new DynamoDBScanExpression());
        List<ScoreEntity> sortedScoresAsList = scores.stream()
                .sorted(Comparator.comparing(ScoreEntity::getScore).reversed())
                .collect(Collectors.toList());
        for (int i = 0; (i < 10) && (i < sortedScoresAsList.size()); i++) {
            PlayerScoreDto currentPlayer = new PlayerScoreDto();
            currentPlayer.setId(sortedScoresAsList.get(i).getPlayerId());
            currentPlayer.setName(sortedScoresAsList.get(i).getPlayerName());
            currentPlayer.setScore(sortedScoresAsList.get(i).getScore());
            currentPlayer.setColor(sortedScoresAsList.get(i).getPlayerColor());
            result.getPlayers().add(currentPlayer);
        }

        return result;
    }
}
