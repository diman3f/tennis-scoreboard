package com.diman_3f.tennis_scoreboard.models;

public enum RegularGamePlayerPoints {
    ZERO, FIFTY, THIRTY, FORTY, ADVANTAGE;

    public static RegularGamePlayerPoints next(RegularGamePlayerPoints player) {
        if (player.ordinal() != ADVANTAGE.ordinal()) {
            return RegularGamePlayerPoints.values()[player.ordinal() + 1];
        } else throw new IllegalArgumentException("Cannot be more Advantage");
    }
}
