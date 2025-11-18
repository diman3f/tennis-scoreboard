package com.diman_3f.tennis_scoreboard.services;

//Извлечь текущий матч по uuid

// передать матча в кальякулятор в калькулятор

// проверить завершение матча на основании полученного dto

// матч завершен сохранить через другой сервис, матч не завершен обновить состояние модели матча


import com.diman_3f.tennis_scoreboard.entities.MatchMapper;
import com.diman_3f.tennis_scoreboard.entities.MatchModelMapper;
import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;


public class MatchScoreController {

    private MatchCreatorService matchCreatorService;
    private MatchScoreCalculationService scoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private MatchMapper matchMapper;


    public MatchScoreController(MatchCreatorService matchCreatorService, MatchScoreCalculationService scoreCalculationService,
                                FinishedMatchesPersistenceService finishedMatchesPersistenceService) {
        this.matchCreatorService = matchCreatorService;
        this.scoreCalculationService = scoreCalculationService;
        this.finishedMatchesPersistenceService = finishedMatchesPersistenceService;
        this.matchMapper = new MatchModelMapper();
    }



    public void action(ActiveMatch match) {

            Match tennisMatchEntity = matchMapper.matchToTennisMatchEntity(match);
            finishedMatchesPersistenceService.saveMatch(tennisMatchEntity);

        //преоброзовать в ScoreModelFinishedDto и вернуть для отображения на vue
    }
}

