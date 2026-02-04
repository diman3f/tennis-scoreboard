package com.diman_3f.tennis_scoreboard.services.score;

import com.diman_3f.tennis_scoreboard.models.SetScore;
import com.diman_3f.tennis_scoreboard.models.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetScoreTest extends SetScore {

    @Test
    public void oneGame() {
        for (int i = 0; i < 23; i++) {
            pointWon(0);
            System.out.println(i);
        }
        State state = pointWon(0);
        Assertions.assertEquals(state, State.WON_ONE);
    }
}
