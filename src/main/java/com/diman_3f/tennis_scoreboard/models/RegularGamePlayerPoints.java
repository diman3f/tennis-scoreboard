package com.diman_3f.tennis_scoreboard.models;

public enum RegularGamePlayerPoints {
    ZERO, FIFTY, THIRTY, FORTY, ADVANTAGE;

    public static RegularGamePlayerPoints next(RegularGamePlayerPoints player) {
        if (player.ordinal() != ADVANTAGE.ordinal()) {
            return RegularGamePlayerPoints.values()[player.ordinal() + 1];
        } else throw new IllegalArgumentException("Cannot be more Advantage");
    }

    public String getNumber() {
        switch (this) {
            case ZERO: return "0";
            case FIFTY: return "15";
            case THIRTY: return "30";
            case FORTY: return "40";
            case ADVANTAGE: return "AD";
            default: throw new IllegalStateException("Отсутствует такое значение");
        }
    }
}
