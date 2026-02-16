package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisRuleThreshold;
import com.diman_3f.tennis_scoreboard.models.MatchScoreModel;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import com.diman_3f.tennis_scoreboard.models.SetMatch;

public class MatchScoreCalculationService {

    private TennisRuleHandler tennisRuleHandler;
    private MatchScoreModel scoreModel;

    public MatchScoreCalculationService(TennisRuleHandler handler) {
        this.tennisRuleHandler = handler;
        this.scoreModel = new MatchScoreModel();
    }
    public OngoingMatch upPoint(int playerId, OngoingMatch match) {
      return match.upPointPlayer(playerId);
    }
}
