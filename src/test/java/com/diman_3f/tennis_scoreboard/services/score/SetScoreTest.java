package com.diman_3f.tennis_scoreboard.services.score;

import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SetScoreTest {
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
    public void shouldOnePlayerWonSet_WhenScoreGameSixZero() {
        setScoreFiveGameAndFortyPointPlayer(INDEX_ONE_PLAYER);
        match.upPointPlayer(INDEX_ONE_PLAYER);
        ScoreDto score = match.getScore();
        Assertions.assertEquals(1, score.getSetOne());
    }

    @Test
    public void shouldOnePlayerScoreSetZero_WhenScoreGameSixFive() {
        setScoreFiveGameAndFortyPointPlayer(INDEX_ONE_PLAYER);
        setScoreFiveGamePlayer(INDEX_OPPOSITE_PLAYER);
        match.upPointPlayer(INDEX_ONE_PLAYER);
        ScoreDto score = match.getScore();
        Assertions.assertEquals(0, score.getSetOne());
    }

    @Test
    public void shouldDontFinishedMatch_WhenScoreSetDontEqualsTwo() {
        setScoreFiveGameAndFortyPointPlayer(INDEX_ONE_PLAYER);
        match.upPointPlayer(INDEX_ONE_PLAYER);
        Assertions.assertEquals(false, match.isMatchFinished());
    }

    @Test
    public void shouldMatchFinished_WhenScoreSetEqualsTwo() {
        setScoreFiveGameAndFortyPointPlayer(INDEX_ONE_PLAYER);
        match.upPointPlayer(INDEX_ONE_PLAYER);
        setScoreFiveGameAndFortyPointPlayer(INDEX_ONE_PLAYER);
        match.upPointPlayer(INDEX_ONE_PLAYER);
        Assertions.assertEquals(true, match.isMatchFinished());
    }
    @Test
    public void shouldOnePlayerWonSet_WhenScoreFiveFiveAndOnePlayerWonGameTwoValue(){
        setScoreFiveGamePlayer(INDEX_ONE_PLAYER);
        setScoreFiveGamePlayer(INDEX_OPPOSITE_PLAYER);
        for (int i = 0; i < 8; i++) {
            match.upPointPlayer(INDEX_ONE_PLAYER);
        }
        ScoreDto score = match.getScore();
        Assertions.assertEquals(score.getSetOne(),1);
    }

    private void setScoreFiveGameAndFortyPointPlayer(int indexPlayer) {
        for (int i = 0; i < 23; i++) {
            match.upPointPlayer(indexPlayer);
        }
    }

    private void setScoreFiveGamePlayer(int indexPlayer) {
        for (int i = 0; i < 20; i++) {
            match.upPointPlayer(indexPlayer);
        }
    }
}
