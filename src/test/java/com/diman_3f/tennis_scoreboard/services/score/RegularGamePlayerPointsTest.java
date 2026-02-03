package com.diman_3f.tennis_scoreboard.services.score;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.diman_3f.tennis_scoreboard.models.RegularGamePlayerPoints;

public class RegularGamePlayerPointsTest {

    @Test
    void shouldFiftyWhenStateZERO(){
        Assertions.assertEquals("FIFTY",String.valueOf(RegularGamePlayerPoints.next(RegularGamePlayerPoints.ZERO)));
    }
}
