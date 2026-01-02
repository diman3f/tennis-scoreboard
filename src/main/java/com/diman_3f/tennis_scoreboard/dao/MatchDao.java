package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.dto.MatchResultDto;
import com.diman_3f.tennis_scoreboard.entities.Match;

import java.util.List;

public interface MatchDao {

    void save(Match entity);
    int count();
    List<MatchResultDto> getMatchWithOffSet(int offset, int limit);
    Match findById(int id);
    List<MatchResultDto> findByName(String name);
    List<MatchResultDto> findAll();
}
