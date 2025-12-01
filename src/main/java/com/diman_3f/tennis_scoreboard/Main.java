package com.diman_3f.tennis_scoreboard;

import com.diman_3f.tennis_scoreboard.dao.JPAMatchDao;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import com.diman_3f.tennis_scoreboard.services.*;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        ApplicationStateInstaller installer = new ApplicationStateInstaller();
        installer.initializeDefaultDatabase();

        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
        UUID uuid = ongoingMatchesService.createCurrentMatch("Ivan", "Oleg");

        OngoingMatch match = ongoingMatchesService.getMatch(String.valueOf(uuid));

        match.setStateMatch(3,5,1,0,0,0, TennisMatchState.REGULAR_STATE);

        System.out.println();





    }
}

