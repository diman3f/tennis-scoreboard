package com.diman_3f.tennis_scoreboard;

import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.services.ApplicationStateInstaller;
import com.diman_3f.tennis_scoreboard.services.MatchCreatorService;
import com.diman_3f.tennis_scoreboard.services.MatchScoreCalculationService;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        ApplicationStateInstaller installer = new ApplicationStateInstaller();
        installer.initializeDefaultDatabase();

        MatchCreatorService matchCreatorService = new MatchCreatorService();
        matchCreatorService.setPlayerDao(new PlayerDao());
        UUID uuid = matchCreatorService.createCurrentMatch("Ivan", "Oleg");

        MatchScoreCalculationService service = new MatchScoreCalculationService(matchCreatorService.getMatch(uuid));

        System.out.println(service.upPoint(1));
        System.out.println(service.upPoint(1));
        System.out.println(service.upPoint(1));
        System.out.println(service.upPoint(2));
        System.out.println(service.upPoint(2));
        System.out.println(service.upPoint(2));
        System.out.println(service.upPoint(2));
        System.out.println(service.upPoint(2));
        System.out.println(service.upPoint(2));
        System.out.println(service.upPoint(2));


    }
}

