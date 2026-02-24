package com.diman_3f.tennis_scoreboard.services.score;

import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import com.diman_3f.tennis_scoreboard.services.MatchScoreCalculationService;
import com.diman_3f.tennis_scoreboard.services.TennisRuleHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TieBreakScoreTest {
    OngoingMatch match;
    private final int INDEX_ONE_PLAYER = 0;
    private final int INDEX_OPPOSITE_PLAYER = 1;

    @BeforeEach
    public void setUp() {
        Player one = new Player(1, "Иван");
        Player two = new Player(2, "Олег");
        this.match = new OngoingMatch(one, two);
    }

    @Test
    public void shouldOnePlayerWonSet_WhenOnePlayerWonSevenPoint() {
        setScoreGameSixSix();
        for (int i = 0; i < 7; i++) {
            match.upPointPlayer(INDEX_ONE_PLAYER);
        }
        ScoreDto score = match.getScore();
        Assertions.assertEquals(score.getSetOne(), 1);
    }

    @Test
    public void shouldOnePlayerWonSet_WhenDifferentPointTwo(){
        setScoreGameSixSix();
        setScorePointSixSix();
        match.upPointPlayer(INDEX_ONE_PLAYER);
        match.upPointPlayer(INDEX_ONE_PLAYER);
        ScoreDto score = match.getScore();
        Assertions.assertEquals(score.getSetOne(), 1);
    }

    private void setScoreGameSixSix() {
        for (int i = 0; i < 23; i++) {
            match.upPointPlayer(INDEX_ONE_PLAYER);
        }
        for (int i = 0; i < 25; i++) {
            match.upPointPlayer(INDEX_OPPOSITE_PLAYER);
        }
        for (int i = 0; i < 4; i++) {
            match.upPointPlayer(INDEX_ONE_PLAYER);
        }
    }

    private void setScorePointSixSix(){
        for (int i = 0; i < 6; i++) {
            match.upPointPlayer(INDEX_ONE_PLAYER);
        }
        for (int i = 0; i < 6; i++) {
            match.upPointPlayer(INDEX_OPPOSITE_PLAYER);
        }
    }
}
