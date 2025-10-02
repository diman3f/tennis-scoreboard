package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisPoints;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.models.ScorePlayer;


/**
 * класс для изменения полей счета ScorePlayer в текущем матче
 */

public class MatchScoreCalculationService extends BaseScoreCalculator {

    public MatchScoreCalculationService(ActiveMatch match) {
        super(match);
    }

    public void upPoint(int playerId) {
        ScorePlayer scorePlayer = match.getByPlayerId(playerId);
        if (isScorePointTied()) {
            PointScoreCalculator pointScoreCalculator = new PointScoreCalculator(match);
            pointScoreCalculator.upGamePlayerScore(playerId);
            return;
        }
        if (isScoreGameTied()) {
            PointScoreCalculator pointScoreCalculator = new PointScoreCalculator(match);
            pointScoreCalculator.upSetPlayerScore(playerId);
        } else {
            upPointPlayerId(playerId);
            upSetPlayerId(scorePlayer);
        }
    }


    private void upPointPlayerId(int playerId) {
        ScorePlayer score = match.getByPlayerId(playerId);
        if (TennisPoints.ZERO.getValue() == score.getPoint()) {
            score.setPoint(TennisPoints.ONE_POINT.getValue());
        } else if (TennisPoints.ONE_POINT.getValue() == score.getPoint()) {
            score.setPoint(TennisPoints.TWO_POINT.getValue());
        } else if (TennisPoints.TWO_POINT.getValue() == score.getPoint()) {
            score.setPoint(TennisPoints.THREE_POINT.getValue());
        } else {
            upGamePlayerId(score);
        }
    }

    private void upGamePlayerId(ScorePlayer score) {
        scoreOne.setPoint(0);
        scoreTwo.setPoint(0);
        score.setGame(score.getGame() + TennisPoints.GAME.getValue());
    }

    private void upSetPlayerId(ScorePlayer score) {

        if (hasWinnerInSet()) {
            scoreOne.setPoint(0);
            scoreTwo.setPoint(0);
            scoreOne.setGame(0);
            scoreTwo.setGame(0);
            score.setSet(score.getSet() + TennisPoints.SET.getValue());
        }
    }

    private boolean hasWinnerInSet() {
        int gameOne = scoreOne.getGame();
        int gameTwo = scoreTwo.getGame();
        if (gameOne >= 6 || gameTwo >= 6) {
            if (gameOne - gameTwo >= 2 || gameTwo - gameOne >= 2) {
                return true;
            }
        }
        return false;
    }

    private boolean isScorePointTied() {
        ScorePlayer scorePlayerOne = match.getScorePlayerOne();
        ScorePlayer scorePlayerTwo = match.getScorePlayerTwo();
        return TennisPoints.THREE_POINT.getValue() == scorePlayerOne.getPoint() &&
                TennisPoints.THREE_POINT.getValue() == scorePlayerTwo.getPoint();
    }

    private boolean isScoreGameTied() {

        return TennisPoints.GAME_EQUALS.getValue() == scoreOne.getGame() &&
                TennisPoints.GAME_EQUALS.getValue() == scoreTwo.getGame();
    }


}
