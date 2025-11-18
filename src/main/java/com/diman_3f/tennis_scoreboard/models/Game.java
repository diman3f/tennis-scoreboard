package com.diman_3f.tennis_scoreboard.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Game {

    private Integer pointOnePlayer;
    private Integer pointTwoPlayer;
    private boolean advantageOnePlayer;
    private boolean advantageTwoPlayer;




    public Game() {
        this.pointOnePlayer = 0;
        this.pointTwoPlayer = 0;

    }


    public void setPointOnePlayer() {
        this.pointOnePlayer++;
    }

    public void setPointTwoPlayer() {
        this.pointTwoPlayer++;
    }


    public void resetFieldDefault() {
        pointOnePlayer = 0;
        pointTwoPlayer = 0;
        advantageOnePlayer = false;
        advantageTwoPlayer = false;
    }


}
