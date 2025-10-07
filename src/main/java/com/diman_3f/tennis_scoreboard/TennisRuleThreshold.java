package com.diman_3f.tennis_scoreboard;

public enum TennisRuleThreshold {
    MIN_DIFFERENT_POINT_TO_WIN_GAME(2),
    MIN_DIFFERENT_POINT_TO_WIN_SET(2),
    MIN_DIFFERENT_GAME_TO_WIN_TIE_BREAK(2),
    MIN_POINT_TO_WIN_TIE_BREAK(7),
    MIN_GAME_TO_WIN_SET(6);

    private int value;

    TennisRuleThreshold(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
