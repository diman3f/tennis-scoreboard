package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisPoints;
import com.diman_3f.tennis_scoreboard.TennisRuleThreshold;
import com.diman_3f.tennis_scoreboard.dto.ActiveMatchDTO;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.models.ScorePlayer;


/**
 * класс для изменения полей счета ScorePlayer в текущем матче
 * после изменения полей должен отдать состояние текущего матча
 */

public class MatchScoreCalculationService extends BaseScoreCalculator {

    private ActiveMatch match;
    private ScorePlayer scoreOne;
    private ScorePlayer scoreTwo;

    public MatchScoreCalculationService(ActiveMatch match) {
        super(match);
        this.match = match;
        this.scoreOne = match.getScorePlayerOne();
        this.scoreTwo = match.getScorePlayerTwo();
    }

    public ActiveMatch getMatch() {
        return match;
    }

    public ActiveMatchDTO upPoint(int playerId) {

        ScorePlayer scorePlayerWinPoint = match.getByPlayerId(playerId);
        if (isScorePointTied()) {
            PointScoreCalculator pointScoreCalculator = new PointScoreCalculator(match);
            pointScoreCalculator.upGamePlayerScore(playerId);
            return createDto(match);
        }
        if (isScoreGameTied()) {
            PointScoreCalculator pointScoreCalculator = new PointScoreCalculator(match);
            pointScoreCalculator.upSetPlayerScore(playerId);
            return createDto(match);
        }
        if (hasWinnerInGame(playerId)) {
            upGamePlayerId(playerId, scorePlayerWinPoint);
        }
        if (hasWinnerInSet(scorePlayerWinPoint)) {
            upSetInScore(playerId, scorePlayerWinPoint);
        } else upPointPlayerId(playerId);
        return createDto(match);
    }

    //todo временный метод переделать на мапинг
    private ActiveMatchDTO createDto(ActiveMatch match) {
        ActiveMatchDTO dto = ActiveMatchDTO.builder()
                .playerOneID(match.getPlayerOneID())
                .playerTwoID(getMatch().getPlayerTwoID())
                .onePlayer(match.getScorePlayerOne().toScore())
                .twoPlayer(match.getScorePlayerTwo().toScore())
                .build();
        return dto;
    }

    private void upPointPlayerId(int playerId) {
        ScorePlayer score = match.getByPlayerId(playerId);
        if (TennisPoints.ZERO.getValue() == score.getPoint()) {
            score.setPoint(TennisPoints.ONE_POINT.getValue());
        } else if (TennisPoints.ONE_POINT.getValue() == score.getPoint()) {
            score.setPoint(TennisPoints.TWO_POINT.getValue());
        } else if (TennisPoints.TWO_POINT.getValue() == score.getPoint()) {
            score.setPoint(TennisPoints.THREE_POINT.getValue());
        }
    }


    private boolean hasWinnerInGame(int playerId) {
        ScorePlayer score = match.getByPlayerId(playerId);
        return hasMinPointOnScoreToWinGame(score) & isRuleByDifferentPointDone();
    }

    private boolean hasMinPointOnScoreToWinGame(ScorePlayer score) {
        return score.getPoint() == TennisPoints.THREE_POINT.getValue();
    }

    private boolean isRuleByDifferentPointDone() {
        return scoreOne.getPoint() <= TennisPoints.TWO_POINT.getValue() ||
                scoreTwo.getPoint() <= TennisPoints.TWO_POINT.getValue();
    }


    private void upGamePlayerId(int playerId, ScorePlayer score) {
        scoreOne.setPoint(TennisPoints.ZERO.getValue());
        scoreTwo.setPoint(TennisPoints.ZERO.getValue());
        score.setGame(score.getGame() + TennisPoints.GAME.getValue());
    }


    private void upSetInScore(int playerId, ScorePlayer score) {
        scoreOne.setPoint(TennisPoints.ZERO.getValue());
        scoreTwo.setPoint(TennisPoints.ZERO.getValue());
        scoreOne.setGame(TennisPoints.ZERO.getValue());
        scoreTwo.setGame(TennisPoints.ZERO.getValue());
        score.setSet(score.getSet() + TennisPoints.SET.getValue());
    }


    private boolean hasWinnerInSet(ScorePlayer score) {
        return hasMinGameOnScoreToWinSet(score) & isRuleByDifferentGameDone();
    }

    private boolean hasMinGameOnScoreToWinSet(ScorePlayer score) {
        return score.getGame() >= TennisRuleThreshold.MIN_GAME_TO_WIN_SET.getValue();
    }

    private boolean isRuleByDifferentGameDone() {
        return scoreOne.getGame() - scoreTwo.getGame() >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_SET.getValue() ||
                scoreTwo.getGame() - scoreOne.getGame() >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_SET.getValue();
    }

    private boolean isScorePointTied() { //todo установка поля больше меньше, для отображения AD, нужно переделать на AD если перевес в одно очко
        ScorePlayer scorePlayerOne = match.getScorePlayerOne();
        ScorePlayer scorePlayerTwo = match.getScorePlayerTwo();
        boolean result = TennisPoints.THREE_POINT.getValue() == scorePlayerOne.getPoint() &&
                TennisPoints.THREE_POINT.getValue() == scorePlayerTwo.getPoint();
        return result;
    }

    private boolean isScoreGameTied() {
        return TennisPoints.GAME_EQUALS.getValue() == scoreOne.getGame() &&
                TennisPoints.GAME_EQUALS.getValue() == scoreTwo.getGame();
    }
}
