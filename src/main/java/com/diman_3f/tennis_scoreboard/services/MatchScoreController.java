package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

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
        OngoingMatch updateMatch = scoreCalculationService.upPoint(id, ongoingMatchesService.getMatch(uuid),0);
        if (updateMatch.isMatchFinished()) {
            finishedMatchesPersistenceService.saveMatch(updateMatch);
            return dtoFormatter.createDto(updateMatch);
        }
        return dtoFormatter.createDto(updateMatch);
    }
}

