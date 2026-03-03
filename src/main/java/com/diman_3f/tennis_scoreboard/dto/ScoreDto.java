package com.diman_3f.tennis_scoreboard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor

public class ScoreDto {
    private int playerOneId;
    private int playerTwoId;
    private String playerOneName;
    private String playerTwoName;
    private int setOne;
    private int gameOne;
    private String pointOne;
    private int setTwo;
    private int gameTwo;
    private String pointTwo;
    private boolean isFinished;
    private String winner;

    public ScoreDto(int playerOneId, int playerTwoId, String playerOneName, String playerTwoName, int setOne, int gameOne, String pointOne, int setTwo, int gameTwo, String pointTwo, boolean isFinished) {
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.setOne = setOne;
        this.gameOne = gameOne;
        this.pointOne = pointOne;
        this.setTwo = setTwo;
        this.gameTwo = gameTwo;
        this.pointTwo = pointTwo;
        this.isFinished = isFinished;
    }

    public ScoreDto(String playerOneName, String playerTwoName, int setOne, int setTwo, boolean isFinished, String winner) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.setOne = setOne;
        this.setTwo = setTwo;
        this.isFinished = isFinished;
        this.winner = winner;
    }
}
