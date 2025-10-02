package com.diman_3f.tennis_scoreboard;

public enum TennisPoints {

    ONE_POINT(15),
    TWO_POINT(30),
    THREE_POINT(40),
    GAME_EQUALS(6),
    GAME(1),
    SET(1),
    ZERO(0);

    private int value;

    TennisPoints(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
