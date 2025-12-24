package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.dto.MatchPlayerName;
import com.diman_3f.tennis_scoreboard.entities.Match;

import java.util.List;

public interface MatchDao {

    void save(Match entity);
    int count();
    Match findById(int id);
    MatchPlayerName findByName(String name);
    List<MatchPlayerName> findAll();
}
