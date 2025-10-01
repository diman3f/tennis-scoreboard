package com.diman_3f.tennis_scoreboard;

import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.model.ActiveMatch;
import com.diman_3f.tennis_scoreboard.service.ApplicationStateInstaller;
import com.diman_3f.tennis_scoreboard.service.MatchCreatorService;
import com.diman_3f.tennis_scoreboard.service.MatchScoreCalculationService;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        ApplicationStateInstaller installer = new ApplicationStateInstaller();
        installer.initializeDefaultDatabase();


        MatchCreatorService matchCreatorService = new MatchCreatorService(new PlayerDao());

        UUID id = matchCreatorService.createCurrentMatch("Ivan", "Oleg");
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

