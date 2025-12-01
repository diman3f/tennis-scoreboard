package com.diman_3f.tennis_scoreboard.entities;

import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

public interface MatchMapper  {
    Match matchToTennisMatchEntity(OngoingMatch match);

}
