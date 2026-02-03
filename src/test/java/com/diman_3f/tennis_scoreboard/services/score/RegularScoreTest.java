package com.diman_3f.tennis_scoreboard.services.score;

import com.diman_3f.tennis_scoreboard.models.RegularGamePlayerPoints;
import com.diman_3f.tennis_scoreboard.models.RegularScore;
import com.diman_3f.tennis_scoreboard.models.Score;
import com.diman_3f.tennis_scoreboard.models.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegularScoreTest extends RegularScore {

    @Test
    public void shouldBeScoreThirty_WhenOnePlayerTwoWinPoint() {
        RegularScore score = new RegularScore();
        for (int i = 0; i < 2; i++) {
            RegularGamePlayerPoints point = score.getScorePlayer(0);
            RegularGamePlayerPoints next = RegularGamePlayerPoints.next(point);
            score.setScorePlayer(0, next);
        }
        Assertions.assertEquals(score.getScorePlayer(0), RegularGamePlayerPoints.THIRTY);
    }

    @Test
    public void shouldBeScoreAdvantage_WhenOnePlayerWinPointFour() {
        RegularScore score = new RegularScore();
        for (int i = 0; i < 4; i++) {
            RegularGamePlayerPoints point = RegularGamePlayerPoints.next(score.getScorePlayer(0));
            score.setScorePlayer(0, point);
        }
        Assertions.assertEquals(score.getScorePlayer(0), RegularGamePlayerPoints.ADVANTAGE);
    }

    @Test
    public void shouldBeForty_WhenTwoScoreAdvantage() {
        setScoreOppositePlayer(1, RegularGamePlayerPoints.ADVANTAGE);
        setScorePlayer(0, RegularGamePlayerPoints.FORTY);
        pointWon(0);
        Assertions.assertEquals(getScoreOppositePlayer(1), RegularGamePlayerPoints.FORTY);
    }

    @Test
    public void shouldBeOnePlayerWonGame_WhenTwoScoreAdvantage() {
        setScoreOppositePlayer(0, RegularGamePlayerPoints.ADVANTAGE);
        setScorePlayer(0, RegularGamePlayerPoints.ADVANTAGE);
        pointWon(0);
        Assertions.assertEquals(pointWon(0), State.WON_ONE);
    }


    @Test
    public void shouldBeScoreAdvantageWhenWon() {
        RegularScore score = new RegularScore();
        for (int i = 0; i < 3; i++) {
            RegularGamePlayerPoints point = score.getScorePlayer(0);
            RegularGamePlayerPoints.next(point);
            score.setScorePlayer(0, point);
        }
    }
}
