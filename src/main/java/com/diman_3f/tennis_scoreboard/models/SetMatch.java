package com.diman_3f.tennis_scoreboard.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetMatch {

    private Integer gameOnePlayer;
    private Integer gameTwoPlayer;
    private Integer pointOneTieBreakPlayer;
    private Integer pointTwoTieBreakPlayer;

    public SetMatch() {
        this.gameOnePlayer = 0;
        this.gameTwoPlayer = 0;
        this.pointOneTieBreakPlayer = 0;
        this.pointTwoTieBreakPlayer = 0;
    }

    public void upGameOnePlayer() {
        this.gameOnePlayer++;
    }

    public void upGameTwoPlayer() {
        this.gameTwoPlayer++;
    }

    public void upPointTieBreakOnePlayer() {
        this.pointOneTieBreakPlayer++;
    }

    public void upPointTieBreakTwoPlayer() {
        this.pointTwoTieBreakPlayer++;
    }

    public void resetFieldDefault() {
        gameOnePlayer = 0;
        gameTwoPlayer = 0;
        pointOneTieBreakPlayer = 0;
        pointTwoTieBreakPlayer = 0;
    }
}
