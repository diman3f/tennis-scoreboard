package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchScoreCalculationServiceTieBreakTest {
   private MatchScoreCalculationService service;
   private OngoingMatch match;

    @BeforeEach
    public void setUp() {
        service = new MatchScoreCalculationService(new TennisRuleHandler());
        Player one = new Player(1, "Иван");
        Player two = new Player(2, "Олег");
        match = new OngoingMatch(one, two);
        match.getSet().setGameOnePlayer(6);
        match.getSet().setGameTwoPlayer(6);
    }

    @Test
    void shouldStartTieBreak_WhenScoreSixSix() {
        match.getSet().setGameOnePlayer(5);
        match.getGame().setPointOnePlayer(3);
        service.upPoint(1, match);
        service.upPoint(1, match);
        Assertions.assertEquals(TennisMatchState.TIEBREAK, (match.getState()));
    }

    @Test
    void upPointInTieBreakOnePlayerFirstPoint() {
        service.upPoint(1, match);
        Assertions.assertEquals(1, match.getSet().getPointOneTieBreakPlayer());
        Assertions.assertEquals(0, match.getSet().getPointTwoTieBreakPlayer());
    }

    @Test
    void upPointWinOnePlayerTieBreak() {
        match.getSet().setPointOneTieBreakPlayer(6);
        match.getSet().setPointTwoTieBreakPlayer(5);
        service.upPoint(1,match);
        Assertions.assertEquals(1, match.getSetOnePlayer());
        Assertions.assertEquals(0, match.getSetTwoPlayer());
        Assertions.assertEquals(0, match.getSet().getPointOneTieBreakPlayer());
        Assertions.assertEquals(0, match.getSet().getPointTwoTieBreakPlayer());
        Assertions.assertEquals(0, match.getGame().getPointOnePlayer());
        Assertions.assertEquals(0, match.getGame().getPointTwoPlayer());
    }
    @Test
    void upPointOnePlayerContinuesTieBreak() {
        match.getSet().setPointOneTieBreakPlayer(5);
        match.getSet().setPointTwoTieBreakPlayer(6);
        service.upPoint(1,match);
        Assertions.assertEquals(0, match.getSetOnePlayer());
        Assertions.assertEquals(0, match.getSetTwoPlayer());
        Assertions.assertEquals(0, match.getGame().getPointOnePlayer());
        Assertions.assertEquals(0, match.getGame().getPointTwoPlayer());
        Assertions.assertEquals(6, match.getSet().getPointOneTieBreakPlayer());
        Assertions.assertEquals(6, match.getSet().getPointTwoTieBreakPlayer());
        Assertions.assertEquals(6, match.getSet().getGameOnePlayer());
        Assertions.assertEquals(6, match.getSet().getGameTwoPlayer());
    }
}

