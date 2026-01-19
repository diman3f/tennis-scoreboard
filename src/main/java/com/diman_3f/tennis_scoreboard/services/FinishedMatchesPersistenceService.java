package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.dao.CrudDao;
import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.entities.MatchModelMapper;
import com.diman_3f.tennis_scoreboard.exception.InternalServerError;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

import java.util.NoSuchElementException;

public class FinishedMatchesPersistenceService {
    private CrudDao<Match> matchDao;
    private MatchModelMapper mapper;

    public FinishedMatchesPersistenceService(CrudDao matchDao) {
        this.matchDao = matchDao;
        this.mapper = new MatchModelMapper();
    }

    public void saveMatch(OngoingMatch match) {
            Match finished = mapper.matchToTennisMatchEntity(match);
            matchDao.save(finished);
    }
}
