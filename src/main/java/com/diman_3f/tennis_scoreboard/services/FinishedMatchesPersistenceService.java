package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.dao.MatchDao;
import com.diman_3f.tennis_scoreboard.entities.Match;

import java.util.NoSuchElementException;

public class FinishedMatchesPersistenceService {
    private MatchDao matchDao;

    public FinishedMatchesPersistenceService(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    public void saveMatch(Match match) {
        try {
            matchDao.save(match);
            System.out.println("запись матча в БД имя игроков, победитель");
        } catch (RuntimeException e) {
            throw new NoSuchElementException("матч не сохранен");
        }
    }
}
