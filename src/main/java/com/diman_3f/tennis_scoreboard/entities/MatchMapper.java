package com.diman_3f.tennis_scoreboard.entities;

import com.diman_3f.tennis_scoreboard.models.ActiveMatch;

public interface MatchMapper  {
    Match matchToTennisMatchEntity(ActiveMatch match);

}
