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
    private int playerOneName;
    private int playerTwoName;
    private int idPlayerOneWinner;
    private int idPlayerTwoWinner;
    private boolean isFinishedMatch;
    private String page;
    private String pointOne;
    private int gameOne;
    private int setOne;
    private String pointTwo;
    private int gameTwo;
    private int setTwo;

}
