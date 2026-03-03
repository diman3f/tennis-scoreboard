package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

public class MatchScoreController {

    private final OngoingMatchesService ongoingMatchesService;
    private final MatchScoreCalculationService scoreCalculationService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    public MatchScoreController(OngoingMatchesService ongoingMatchesService, MatchScoreCalculationService scoreCalculationService,
                                FinishedMatchesPersistenceService finishedMatchesPersistenceService) {
        this.ongoingMatchesService = ongoingMatchesService;
        this.scoreCalculationService = scoreCalculationService;
        this.finishedMatchesPersistenceService = finishedMatchesPersistenceService;
    }

    public ScoreDto addPoint(int numberPlayer, String uuid) {
        OngoingMatch updateMatch = scoreCalculationService.upPoint(numberPlayer, ongoingMatchesService.getMatch(uuid));
        if (updateMatch.isMatchFinished()) {
            finishedMatchesPersistenceService.saveMatch(updateMatch);
            return new ScoreDto(updateMatch.getPlayerOne().getName(),updateMatch.getPlayerTwo().getName(),
                    updateMatch.getSetOnePlayer(), updateMatch.getSetTwoPlayer(),updateMatch.isMatchFinished(),
                    updateMatch.getWinner().getName());
        }
        return updateMatch.getScore();
    }
}

