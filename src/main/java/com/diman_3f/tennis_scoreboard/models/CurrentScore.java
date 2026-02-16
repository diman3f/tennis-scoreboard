package com.diman_3f.tennis_scoreboard.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentScore {
    private String nameOne;
    private String nameTwo;
    private int setOne;
    private int setTwo;
    private int gameOne;
    private int gameTwo;
    private String pointOne;
    private String pointTwo;

    public CurrentScore() {
        this.pointOne = "0";
        this.pointTwo = "0";
    }
}
