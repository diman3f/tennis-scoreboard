package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.dto.MatchResultDto;


import java.util.List;

public interface CrudDao<T> {
    void save(T o);
    int count();
    List<MatchResultDto> getMatchWithOffSet(int offset, int limit);
    List<MatchResultDto> findByName(String name);
}
