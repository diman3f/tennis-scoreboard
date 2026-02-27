package com.diman_3f.tennis_scoreboard.utils;

public final class Validator {
    private final static int LENGTH_NAME = 15;

    private Validator() {
        throw new RuntimeException("Static classes should not be created");
    }

    public static boolean isValidName(String name) {
        if (name != null) {
            return name.matches("^[А-ЯЁ][а-яё]+$") || name.matches("^[A-Z][a-z]+$");
        }
        return false;
    }

    public static boolean isValidLength(String name) {
        if (name != null) {
            return name.length() < LENGTH_NAME;
        }
        return false;
    }
}
