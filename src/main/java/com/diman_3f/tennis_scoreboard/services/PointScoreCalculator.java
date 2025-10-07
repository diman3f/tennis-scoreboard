package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisPoints;
import com.diman_3f.tennis_scoreboard.TennisRuleThreshold;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.models.ScorePlayer;

/**
 * Класс для изменения счета ScorePlayer при "равно" и "тайбрейке"
 */

public class PointScoreCalculator extends BaseScoreCalculator {


    public PointScoreCalculator(ActiveMatch match) {
        super(match);
    }

    public void upGamePlayerScore(int playerId) {
        ScorePlayer score = match.getByPlayerId(playerId);
        score.setEqualsGame(score.getEqualsGame() + TennisPoints.EQUALS_GAME.getValue());
        if (hasWinInGameEquals()) {
            scoreOne.setPoint(TennisPoints.ZERO.getValue());
            scoreOne.setEqualsGame(TennisPoints.ZERO.getValue());
            scoreTwo.setPoint(TennisPoints.ZERO.getValue());
            scoreTwo.setEqualsGame(TennisPoints.ZERO.getValue());
            score.setGame(TennisPoints.GAME.getValue());
        }
    }

    private boolean hasWinInGameEquals() {
        int equalsGameOne = scoreOne.getEqualsGame();
        int equalsGameTwo = scoreTwo.getEqualsGame();
        int resultOne = equalsGameOne - equalsGameTwo;
        int resultTwo = equalsGameTwo - equalsGameOne;
        return resultOne >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_GAME.getValue() ||
                resultTwo >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_GAME.getValue();


    }

    public void upSetPlayerScore(int playerId) {
        ScorePlayer score = match.getByPlayerId(playerId);
        score.setEqualsGame(score.getEqualsGame() + TennisPoints.GAME.getValue());
        if (hasWinnerTieBreak()) {
            scoreOne.setGame(TennisPoints.ZERO.getValue());
            scoreTwo.setGame(TennisPoints.ZERO.getValue());
            scoreOne.setEqualsGame(TennisPoints.ZERO.getValue());
            scoreTwo.setEqualsGame(TennisPoints.ZERO.getValue());
            score.setSet(TennisPoints.SET.getValue());
        }
    }

    private boolean hasWinnerTieBreak() {
        int equalsGameOne = scoreOne.getEqualsGame();
        int equalsGameTwo = scoreTwo.getEqualsGame();
        int resultOne = equalsGameOne - equalsGameTwo;
        int resultTwo = equalsGameTwo - equalsGameOne;

        if (equalsGameOne >= TennisRuleThreshold.MIN_POINT_TO_WIN_TIE_BREAK.getValue() ||
                equalsGameTwo >= TennisRuleThreshold.MIN_POINT_TO_WIN_TIE_BREAK.getValue()) {
            if (resultOne >= TennisRuleThreshold.MIN_DIFFERENT_GAME_TO_WIN_TIE_BREAK.getValue() ||
                    resultTwo >= TennisRuleThreshold.MIN_DIFFERENT_GAME_TO_WIN_TIE_BREAK.getValue() ) {
                return true;
            }
        }
        return false;
    }
}
