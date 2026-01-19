package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchScoreCalculationServiceTest {

    MatchScoreCalculationService service;
    OngoingMatch match;

    @BeforeEach
    public void setUp(){
        service = new MatchScoreCalculationService(new TennisRuleHandler());
        Player one = new Player(1, "Иван");
        Player two = new Player(2, "Олег");
        match = new OngoingMatch(one, two);
    }


}
