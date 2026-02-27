package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.dao.CrudDao;
import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.entities.MatchModelMapper;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

public class FinishedMatchesPersistenceService {
    private final CrudDao<Match> matchDao;
    private final MatchModelMapper mapper;

    public FinishedMatchesPersistenceService(CrudDao<Match> matchDao) {
        this.matchDao = matchDao;
        this.mapper = new MatchModelMapper();
    }
    public void saveMatch(OngoingMatch match) {
            Match finished = mapper.matchToTennisMatchEntity(match);
            matchDao.save(finished);
    }
}
