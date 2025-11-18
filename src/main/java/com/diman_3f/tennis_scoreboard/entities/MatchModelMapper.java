package com.diman_3f.tennis_scoreboard.entities;

import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchModelMapper implements MatchMapper {



    @Override
    public Match matchToTennisMatchEntity(ActiveMatch match) {
        Match tennisMatchEntity = new Match();
        tennisMatchEntity.setPlayer1(match.getPlayerOneId());
        tennisMatchEntity.setPlayer2(match.getPlayerTwoId());
        tennisMatchEntity.setWinner(match.getIdPlayerWinnerMatch());
        return tennisMatchEntity;
    }
}
