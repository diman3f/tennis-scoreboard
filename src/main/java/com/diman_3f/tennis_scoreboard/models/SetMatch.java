package com.diman_3f.tennis_scoreboard.models;

import lombok.Getter;

@Getter
public class SetMatch {

    private Integer gameOnePlayer;
    private Integer gameTwoPlayer;
    private Integer pointOnePlayer;
    private Integer pointTwoPlayer;

    public SetMatch() {
        this.gameOnePlayer = 0;
        this.gameTwoPlayer = 0;
        this.pointOnePlayer = 0;
        this.pointTwoPlayer = 0;
    }

    public void upGameOnePlayer() {
        this.gameOnePlayer++;
    }

    public void upGameTwoPlayer() {
        this.gameTwoPlayer++;
    }

    public void upPointTieBreakOnePlayer() {
        this.pointOnePlayer++;
    }

    public void upPointTieBreakTwoPlayer() {
        this.pointTwoPlayer++;
    }

    public void resetFieldDefault() {
        gameOnePlayer = 0;
        gameTwoPlayer = 0;
        pointOnePlayer = 0;
        pointTwoPlayer = 0;
    }
}
