package com.diman_3f.tennis_scoreboard.entities;

import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchModelMapper implements MatchMapper {
    @Override
    public Match matchToTennisMatchEntity(OngoingMatch match) {
        return Match.builder()
                .player1(match.getPlayerOne())
                .player2(match.getPlayerTwo())
                .winner(match.getWinner())
                .build();
    }
}
