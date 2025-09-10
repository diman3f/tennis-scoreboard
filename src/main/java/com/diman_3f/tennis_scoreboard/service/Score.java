package com.diman_3f.tennis_scoreboard.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Score {
    private int matchPlayerOne = 0;
    private int setPlayerOne = 0;
    private int gamePlayerOne = 0;
    private int pointPlayerOne;

    private int matchPlayerTwo = 0;
    private int setPlayerTwo = 0;
    private int gamePlayerTwo = 0;
    private int pointPlayerTwo;


}
