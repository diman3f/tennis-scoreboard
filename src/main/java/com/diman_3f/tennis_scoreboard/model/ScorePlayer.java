package com.diman_3f.tennis_scoreboard.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScorePlayer {

    private int tieBreak = 0;
    private int equalsGame = 0; // TODO: 24.09.2025 переделать нейминг  
    private int equalsSetGame = 0; // TODO: 24.09.2025 переделать нейминг
    private int set = 0;
    private int game = 0;
    private int point = 0;
}
