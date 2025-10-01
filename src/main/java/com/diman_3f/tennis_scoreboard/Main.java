package com.diman_3f.tennis_scoreboard;

import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.services.ApplicationStateInstaller;
import com.diman_3f.tennis_scoreboard.services.MatchCreatorService;
import com.diman_3f.tennis_scoreboard.services.MatchScoreCalculationService;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        ApplicationStateInstaller installer = new ApplicationStateInstaller();
        installer.initializeDefaultDatabase();


        MatchCreatorService matchCreatorService = new MatchCreatorService(new PlayerDao());
        matchCreatorService.createCurrentMatch("Ivan", "Oleg");
        UUID id = matchCreatorService.getUuid();
        System.out.println(id);


        MatchScoreCalculationService service = new MatchScoreCalculationService(matchCreatorService);
        UUID uuid = matchCreatorService.getUuid();
        ActiveMatch match = matchCreatorService.getMatch(uuid);
        service.upPoint(1, match);
        service.upPoint(1, match);
        service.upPoint(1, match);
        service.upPoint(1, match);
        service.upPoint(1, match);
        service.upPoint(1, match);
        service.upPoint(1, match);
        service.upPoint(1, match);


    }
}

