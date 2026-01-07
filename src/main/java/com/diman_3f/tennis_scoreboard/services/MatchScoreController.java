package com.diman_3f.tennis_scoreboard.services;

//Извлечь текущий матч по uuid

// передать матча в кальякулятор в калькулятор

// проверить завершение матча на основании полученного dto

// матч завершен сохранить через другой сервис, матч не завершен обновить состояние модели матча


import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.entities.MatchMapper;
import com.diman_3f.tennis_scoreboard.entities.MatchModelMapper;
import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

import java.util.UUID;


public class MatchScoreController {

    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService scoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private ScoreDtoFormatter dtoFormatter;


    public MatchScoreController(OngoingMatchesService ongoingMatchesService, MatchScoreCalculationService scoreCalculationService,
                                FinishedMatchesPersistenceService finishedMatchesPersistenceService) {
        this.ongoingMatchesService = ongoingMatchesService;
        this.scoreCalculationService = scoreCalculationService;
        this.finishedMatchesPersistenceService = finishedMatchesPersistenceService;
        this.dtoFormatter = new ScoreDtoFormatter();
    }

    public ScoreDto addPoint(String playerId, String uuid) {
        int id = Integer.parseInt(playerId);
        OngoingMatch updateMatch = scoreCalculationService.upPoint(id, ongoingMatchesService.getMatch(uuid));
        if (updateMatch.isMatchFinished()) {
            finishedMatchesPersistenceService.saveMatch(updateMatch);
            return dtoFormatter.createDto(updateMatch);
        }
        return dtoFormatter.createDto(updateMatch);
    }
}

