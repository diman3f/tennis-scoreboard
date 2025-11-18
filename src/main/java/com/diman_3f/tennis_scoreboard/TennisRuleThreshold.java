package com.diman_3f.tennis_scoreboard;

public enum TennisRuleThreshold {
    MIN_DIFFERENT_POINT_TO_WIN_GAME(2),
    MIN_DIFFERENT_POINT_TO_WIN_MATCH(2),
    MIN_DIFFERENT_GAME_TO_WIN_SET(2),
    MIN_DIFFERENT_POINT_TIE_BREAK_TO_WIN_TIE_BREAK(2),
    MIN_POINT_TO_WIN_TIE_BREAK(7),
    MIN_POINT_TO_WIN_GAME(4),
    MIN_POINT_TO_DONE_EQUALS_GAME(3),
    MIN_GAME_TO_DONE_TIE_BREAK(6),
    MIN_GAME_TO_WIN_SET(6),
    MIN_SET_TO_WIN_MATCH(2);

    private int value;

    TennisRuleThreshold(int value) {
        this.value = value;
    }



    public int getValue() {
        return value;
    }
}
