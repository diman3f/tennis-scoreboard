package com.diman_3f.tennis_scoreboard;

import com.diman_3f.tennis_scoreboard.dao.JPAMatchDao;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.services.*;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        ApplicationStateInstaller installer = new ApplicationStateInstaller();
        installer.initializeDefaultDatabase();

        MatchCreatorService matchCreatorService = new MatchCreatorService();
        matchCreatorService.setPlayerDao(new PlayerDao());
        UUID uuid = matchCreatorService.createCurrentMatch("Ivan", "Oleg");


        ActiveMatch match = matchCreatorService.getMatch(String.valueOf(uuid));

        MatchScoreCalculationService service = new MatchScoreCalculationService(match);

        match.setStateMatch(3,5,1,0,0,0, TennisMatchState.REGULAR_STATE);
        service.upPoint(1);

        System.out.println();





        FinishedMatchesPersistenceService finished = new FinishedMatchesPersistenceService(new JPAMatchDao());

        MatchScoreController controller = new MatchScoreController(matchCreatorService,service,finished);
        controller.addPoint(String.valueOf(uuid));
        System.out.println();




    }
}

