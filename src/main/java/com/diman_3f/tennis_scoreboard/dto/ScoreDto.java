package com.diman_3f.tennis_scoreboard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

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
}
