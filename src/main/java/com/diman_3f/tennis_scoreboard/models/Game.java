package com.diman_3f.tennis_scoreboard.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

    private int pointOnePlayer;
    private int pointTwoPlayer;
    public void setPointOnePlayer() {
        this.pointOnePlayer++;
    }
    public void setPointTwoPlayer() {
        this.pointTwoPlayer++;
    }
    public void resetFieldDefault() {
        pointOnePlayer = 0;
        pointTwoPlayer = 0;

    }

    public void setPointOne(int point) {
        this.pointOnePlayer = point;
    }
    public void setPointTwo(int point) {
        this.pointTwoPlayer = point;
    }


}