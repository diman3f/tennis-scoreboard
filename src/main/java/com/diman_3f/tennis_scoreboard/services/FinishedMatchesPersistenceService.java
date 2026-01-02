package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.dao.MatchDao;
import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.entities.MatchModelMapper;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

import java.util.NoSuchElementException;

public class FinishedMatchesPersistenceService {
    private MatchDao matchDao;
    private MatchModelMapper mapper;

    public FinishedMatchesPersistenceService(MatchDao matchDao) {
        this.matchDao = matchDao;
        this.mapper = new MatchModelMapper();

    }

    public void saveMatch(OngoingMatch match) {
        try {
            matchDao.save(mapper.matchToTennisMatchEntity(match));
            System.out.println("запись матча в БД имя игроков, победитель"+ match.getWinner());
        } catch (RuntimeException e) {
            throw new NoSuchElementException("матч не сохранен");
        }
    }
}
