package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MatchScoreCalculationAdvantage {
    private MatchScoreCalculationService service;
    private OngoingMatch match;

    @BeforeEach
    public void setUp() {
        service = new MatchScoreCalculationService(new TennisRuleHandler());
        Player one = new Player(1, "Иван");
        Player two = new Player(2, "Олег");
        match = new OngoingMatch(one, two);
    }

    @Test
    void playerOneShouldHaveAdvantage_WhenScoreIsDeuceAndTheyWinNextPoint() {
        testAdvantageForPlayer(1, true, false);
    }

    @Test
    void playerTwoShouldHaveAdvantage_WhenScoreIsDeuceAndTheyWinNextPoint() {
        testAdvantageForPlayer(2, false, true);
    }

    @Test
    void advantageShouldBeLost_WhenOnePlayerWithAdvantageLosesPoint() {
        testAdvantageForPlayer(1, true, false);
        service.upPoint(2, match);
        Assertions.assertFalse(match.isAdvantageOnePlayer());
    }

    @Test
    void advantageShouldBeLost_WhenTwoPlayerWithAdvantageLosesPoint() {
        testAdvantageForPlayer(2, false, true);
        service.upPoint(1, match);
        Assertions.assertFalse(match.isAdvantageTwoPlayer());
    }


    private void testAdvantageForPlayer(int playerWinnerPoint, boolean expectedAdvantageOne, boolean expectedAdvantageTwo) {
        match.getGame().setPointOnePlayer(3);
        match.getGame().setPointTwoPlayer(3);
        service.upPoint(playerWinnerPoint, match);
        Assertions.assertEquals(expectedAdvantageOne, match.isAdvantageOnePlayer());
        Assertions.assertEquals(expectedAdvantageTwo, match.isAdvantageTwoPlayer());
        Assertions.assertEquals(0, match.getSet().getGameOnePlayer());
        Assertions.assertEquals(0, match.getSet().getGameTwoPlayer());
        Assertions.assertEquals(TennisMatchState.ADVANTAGE, match.getState());
    }

    @Test
    void shouldWinGameOnePlayer_WhenGameScorePointThreeTwo() {
        match.getGame().setPointOnePlayer(3);
        match.getGame().setPointTwoPlayer(2);
        service.upPoint(1, match);
        Assertions.assertEquals(1, match.getSet().getGameOnePlayer());
        Assertions.assertEquals(0, match.getSet().getGameTwoPlayer());
        Assertions.assertEquals(0, match.getGame().getPointOnePlayer());
        Assertions.assertEquals(0, match.getGame().getPointTwoPlayer());
        Assertions.assertEquals(TennisMatchState.REGULAR_STATE, match.getState());
    }
}
