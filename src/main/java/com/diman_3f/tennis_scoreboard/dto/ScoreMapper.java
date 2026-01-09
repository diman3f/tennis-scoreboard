package com.diman_3f.tennis_scoreboard.dto;

import com.diman_3f.tennis_scoreboard.models.ScorePlayer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScoreMapper {
    ScoreMapper INSTANCE = Mappers.getMapper(ScoreMapper.class);

    Score toScore(ScorePlayer scorePlayer);
}
