package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisPoints;
import com.diman_3f.tennis_scoreboard.TennisRuleThreshold;
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
            upSetInScore(scorePlayer);
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
        scoreOne.setPoint(TennisPoints.ZERO.getValue());
        scoreTwo.setPoint(TennisPoints.ZERO.getValue());
        score.setGame(score.getGame() + TennisPoints.GAME.getValue());
    }

    private void upSetInScore(ScorePlayer score) {
        if (hasWinnerInSet()) {
            scoreOne.setPoint(TennisPoints.ZERO.getValue());
            scoreTwo.setPoint(TennisPoints.ZERO.getValue());
            scoreOne.setGame(TennisPoints.ZERO.getValue());
            scoreTwo.setGame(TennisPoints.ZERO.getValue());
            score.setSet(score.getSet() + TennisPoints.SET.getValue());
        }
    }

    // набранно ли минимальное количество геймов на счете чтобы начислить сет
    // выполнено ли условие по разнице геймов для начисления сета

    private boolean hasWinnerInSet() {
        int gameOne = scoreOne.getGame();
        int gameTwo = scoreTwo.getGame();
        if (gameOne >= TennisRuleThreshold.MIN_GAME_TO_WIN_SET.getValue() ||
                gameTwo >= TennisRuleThreshold.MIN_GAME_TO_WIN_SET.getValue()) {
            if (gameOne - gameTwo >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_SET.getValue() ||
                    gameTwo - gameOne >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_SET.getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasMinGameOnScoreToWinSet(ScorePlayer score) {
        return score.getGame() >= TennisRuleThreshold.MIN_GAME_TO_WIN_SET.getValue();
    }
    private boolean isRuleByDifferentGameDone(ScorePlayer score) {
       return score.getGame() - scoreTwo.getGame() >= TennisRuleThreshold.MIN_GAME_TO_WIN_SET.getValue();
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
