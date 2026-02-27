package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

public class MatchScoreCalculationService {

    public MatchScoreCalculationService() {
    }
    public OngoingMatch upPoint(int playerId, OngoingMatch match) {
      return match.upPointPlayer(playerId);
    }
}
