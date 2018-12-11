package com.serverlessquiz.scoreservice.dto;

import java.util.ArrayList;

public class ResponseDto {

    private ArrayList<PlayerScoreDto> players;

    public ArrayList<PlayerScoreDto> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerScoreDto> players) {
        this.players = players;
    }
}
