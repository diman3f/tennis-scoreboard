package com.diman_3f.tennis_scoreboard.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ActiveMatchDTO {
    private int playerOneID;
    private int playerTwoID;
    private Score onePlayer;
    private Score twoPlayer;
    private boolean isActive;
    private boolean isTieBreak;
    private boolean isGameEquals;
}
