package com.diman_3f.tennis_scoreboard;

import com.diman_3f.tennis_scoreboard.dao.JPAMatchDao;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import com.diman_3f.tennis_scoreboard.services.*;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {




        System.out.println(isValidName("Иван Иванов"));





    }
    public static boolean isValidName(String name) {
        boolean result;
        result =  name.matches("^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+$");
        return result;
    }
}

