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
    private String nameWinner;
    private boolean isFinishedMatch;
    private String page;
    private String pointOne;
    private int gameOne;
    private int setOne;
    private String pointTwo;
    private int gameTwo;
    private int setTwo;

}
