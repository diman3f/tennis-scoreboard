package com.diman_3f.tennis_scoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchResultDto {
    private String namePlayerOne;
    private String namePlayerTwo;
    private String winner;
}
