package com.diman_3f.tennis_scoreboard.model;


import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveMatch {

    private UUID id;
    private Long playerOneID ;
    private Long playerTwoID ;
    private ScorePlayer scorePlayerOne;
    private ScorePlayer scorePlayerTwo;
    private Map<Long, ScorePlayer> scorePlayerMap;

    public ScorePlayer getByPlayerId(Long playerId) {
        if(playerId.equals(playerOneID)) {
            return scorePlayerOne;
        } else {
            return scorePlayerTwo;
        }
    }

}
