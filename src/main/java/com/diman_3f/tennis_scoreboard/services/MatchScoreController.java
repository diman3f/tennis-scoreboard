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

    public ScoreDto addPoint(int numberPlayer, String uuid) {
        OngoingMatch updateMatch = scoreCalculationService.upPoint(numberPlayer, ongoingMatchesService.getMatch(uuid));
        if (updateMatch.isMatchFinished()) {
            finishedMatchesPersistenceService.saveMatch(updateMatch);
            ScoreDto scoreDto = new ScoreDto();
            scoreDto.setFinished(true);
            return scoreDto;
        }
        ScoreDto score = updateMatch.getScore();
        return score;
    }
}

