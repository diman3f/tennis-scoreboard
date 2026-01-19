package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchScoreCalculationGameScore {
    private MatchScoreCalculationService service;
    private OngoingMatch match;
    @BeforeEach
    public void setUp() {
        service = new MatchScoreCalculationService(new TennisRuleHandler());
        Player one = new Player( "Иван");
        Player two = new Player( "Олег");
        one.setId(1);
        two.setId(2);
        match = new OngoingMatch(one, two);
    }
    @Test
    void shouldMatchFinished_WhenSetTwo() {
        match.setSetOnePlayer(1);
        match.setSetTwoPlayer(1);
        match.getSet().setGameOnePlayer(5);
        match.getSet().setGameTwoPlayer(0);
        match.getGame().setPointOnePlayer(3);
        match.getGame().setPointTwoPlayer(0);
        service.upPoint(1, match);
        Assertions.assertEquals(TennisMatchState.FINISHED, match.getState());
    }
}
