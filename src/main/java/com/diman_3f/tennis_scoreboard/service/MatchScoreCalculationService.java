package com.diman_3f.tennis_scoreboard.service;

import com.diman_3f.tennis_scoreboard.model.ActiveMatch;
import com.diman_3f.tennis_scoreboard.model.ScorePlayer;

import java.util.UUID;

public class MatchScoreCalculationService {

    private UUID uuid;
    private MatchCreatorService matchCreatorService;
    private final static int ONE_POINT = 15;
    private final static int TWO_POINT = 30;
    private final static int THREE_POINT = 40;
    private final static int ONE_GAME = 1;
    private final static int ONE_SET = 1;



    public MatchScoreCalculationService(MatchCreatorService matchCreatorService) {
        this.matchCreatorService = matchCreatorService;
    }

    public MatchScoreCalculationService() {

    }


    // если point player 1 == 40  point play 2 == 40 играем равно
    //


    public void upPoint(Long playerId, ActiveMatch match) {
        ScorePlayer scorePlayer = match.getByPlayerId(playerId);
        if(isScoreTied(match)) {
            System.out.println("привет из блока ничья");
        }

        upPointPlayerId(playerId, scorePlayer);



    }

    private void upPointPlayerId(Long playerId, ScorePlayer score) {
        if (score.getPoint() == 0) {
            score.setPoint(ONE_POINT);
        } else if (score.getPoint() == ONE_POINT) {
            score.setPoint(TWO_POINT);
        } else if (score.getPoint() == TWO_POINT) {
            score.setPoint(THREE_POINT);
        } else {
            score.setPoint(0);
            upGamePlayerId(playerId, score);
        }
    }

    private void upGamePlayerId(Long playerId, ScorePlayer score) {
        score.setGame(score.getGame() + ONE_GAME);
    }


    private boolean isScoreTied(ActiveMatch match) {
        ScorePlayer scorePlayerOne = match.getScorePlayerOne();
        ScorePlayer scorePlayerTwo = match.getScorePlayerTwo();
        if(scorePlayerOne.getPoint() == THREE_POINT && scorePlayerTwo.getPoint() == THREE_POINT){
            return true;
        } else return false;
    }



}
