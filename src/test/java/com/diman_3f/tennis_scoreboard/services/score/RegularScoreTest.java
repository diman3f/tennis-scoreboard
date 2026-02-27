package com.diman_3f.tennis_scoreboard.services.score;

import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegularScoreTest {

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
    public void shouldPlayerWonGame_WhenWonFourPoints() {
        for (int i = 0; i < 4; i++) {
            match.upPointPlayer(INDEX_ONE_PLAYER);
        }
        ScoreDto score = match.getScore();
        Assertions.assertEquals(1, score.getGameOne());
    }

    @Test
    public void shouldAdvantageOnePlayer_WhenScoreFortyFortyOnePlayerWonPoint() {
        changeScoreOnForty();
        match.upPointPlayer(INDEX_ONE_PLAYER);
        ScoreDto score = match.getScore();
        Assertions.assertEquals("AD", score.getPointOne());
    }

    @Test
    public void shouldFortyOnePlayer_WhenScoreAdvantageOnePlayerAndTwoPlayerWinPoint() {
        changeScoreOnForty();
        match.upPointPlayer(INDEX_ONE_PLAYER);
        match.upPointPlayer(INDEX_OPPOSITE_PLAYER);
        ScoreDto score = match.getScore();
        Assertions.assertEquals("40", score.getPointOne());
    }

    @Test
    public void shouldOnePlayerWonGame_WhenScoreOnePlayerAdvantage() {
        changeScoreOnForty();
        match.upPointPlayer(INDEX_ONE_PLAYER);
        match.upPointPlayer(INDEX_ONE_PLAYER);
        ScoreDto score = match.getScore();
        Assertions.assertEquals(1, score.getGameOne());
    }

    private void changeScoreOnForty() {
        for (int i = 0; i < 3; i++) {
            match.upPointPlayer(INDEX_ONE_PLAYER);
        }
        for (int i = 0; i < 3; i++) {
            match.upPointPlayer(INDEX_OPPOSITE_PLAYER);
        }
    }
}
