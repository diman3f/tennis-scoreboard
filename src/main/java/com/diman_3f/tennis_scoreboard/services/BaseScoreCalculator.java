package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.models.ScorePlayer;

public abstract class BaseScoreCalculator {
    protected ScorePlayer scoreOne;
    protected ScorePlayer scoreTwo;
    protected ActiveMatch match;

    protected BaseScoreCalculator(ActiveMatch match){
        this.scoreOne = match.getScorePlayerOne();
        this.scoreTwo = match.getScorePlayerTwo();
        this.match = match;

    }
}
