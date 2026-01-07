package com.diman_3f.tennis_scoreboard.context;


public final class Validator {
    private final static int LENGTH_NAME = 25;

    private Validator() {
         throw new RuntimeException("Не создавайте объект статического класса");
    }

    public static boolean isValidName(String name) {
        boolean result;
       result =  name.matches("^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+$");
       return result;
    }
    public static boolean isValidLength(String name) {
       return name.length() < LENGTH_NAME;
    }
}
