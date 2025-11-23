package com.diman_3f.tennis_scoreboard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor

public class ScoreDto {
    private int playerOneID;
    private int playerTwoID;

    private String pointOne;
    private int gameOne;
    private int setOne;

    private String pointTwo;
    private int gameTwo;
    private int setTwo;

}
