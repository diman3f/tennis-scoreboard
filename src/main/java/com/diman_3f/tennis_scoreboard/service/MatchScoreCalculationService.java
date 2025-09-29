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
    private final static int GAME_EQUALS = 6;


    public MatchScoreCalculationService(MatchCreatorService matchCreatorService) {
        this.matchCreatorService = matchCreatorService;
    }


    public void upPoint(Long playerId, ActiveMatch match) {
        ScorePlayer scorePlayer = match.getByPlayerId(playerId);
        if (isScorePointTied(match)) {
            PointScoreCalculator pointScoreCalculator = new PointScoreCalculator(match);
            pointScoreCalculator.upPointEqualsGame(playerId);
            return;
        }
        if (isScoreGameTied(match)) {
            PointScoreCalculator pointScoreCalculator = new PointScoreCalculator(match);
            pointScoreCalculator.upGameEqualsSet(playerId);
        } else {
            upPointPlayerId(playerId, match);
            upSetPlayerId(scorePlayer, match );
        }

    }


    private void upPointPlayerId(Long playerId, ActiveMatch match) {
        ScorePlayer score = match.getByPlayerId(playerId);
        if (score.getPoint() == 0) {
            score.setPoint(ONE_POINT);
        } else if (score.getPoint() == ONE_POINT) {
            score.setPoint(TWO_POINT);
        } else if (score.getPoint() == TWO_POINT) {
            score.setPoint(THREE_POINT);
        } else {
            upGamePlayerId(score, match);
        }
    }

    private void upGamePlayerId(ScorePlayer score, ActiveMatch match) {
        match.getScorePlayerOne().setPoint(0);
        match.getScorePlayerTwo().setPoint(0);
        score.setGame(score.getGame() + ONE_GAME);
    }

    private void upSetPlayerId(ScorePlayer score, ActiveMatch match) {
        ScorePlayer scorePlayerOne = match.getScorePlayerOne();
        ScorePlayer scorePlayerTwo = match.getScorePlayerTwo();
        int gameOne = scorePlayerOne.getGame();
        int gameTwo = scorePlayerTwo.getGame();

        if (scorePlayerOne.getGame() >= 6 || scorePlayerTwo.getGame() >= 6) {
            if (gameOne - gameTwo >= 2 || gameTwo - gameOne >= 2) {
                match.getScorePlayerOne().setPoint(0);
                match.getScorePlayerTwo().setPoint(0);
                match.getScorePlayerOne().setGame(0);
                match.getScorePlayerTwo().setGame(0);
                score.setSet(score.getSet() + ONE_SET);
            }
        }
    }


    private boolean isScorePointTied(ActiveMatch match) {
        ScorePlayer scorePlayerOne = match.getScorePlayerOne();
        ScorePlayer scorePlayerTwo = match.getScorePlayerTwo();
        if (scorePlayerOne.getPoint() == THREE_POINT && scorePlayerTwo.getPoint() == THREE_POINT) {
            return true;
        } else return false;
    }

    private boolean isScoreGameTied(ActiveMatch match) {
        ScorePlayer scorePlayerOne = match.getScorePlayerOne();
        ScorePlayer scorePlayerTwo = match.getScorePlayerTwo();
        if (scorePlayerOne.getGame() == GAME_EQUALS && scorePlayerTwo.getGame() == GAME_EQUALS) {
            return true;
        } else return false;
    }


}
