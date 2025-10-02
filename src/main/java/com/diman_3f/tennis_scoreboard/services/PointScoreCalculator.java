package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisPoints;
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
        score.setEqualsGame(score.getEqualsGame() + TennisPoints.ONE_POINT.getValue());
        if (hasWinInGameEquals()) {
            scoreOne.setPoint(0);
            scoreOne.setEqualsGame(0);
            scoreTwo.setPoint(0);
            scoreTwo.setEqualsGame(0);
            score.setGame(TennisPoints.GAME.getValue());
        }
    }

    private boolean hasWinInGameEquals() {
        int equalsGameOne = scoreOne.getEqualsGame();
        int equalsGameTwo = scoreTwo.getEqualsGame();
        int resultOne = equalsGameOne - equalsGameTwo;
        int resultTwo = equalsGameTwo - equalsGameOne;
        return resultOne >= 2 || resultTwo >= 2;


    }

    public void upSetPlayerScore(int playerId) {
        ScorePlayer score = match.getByPlayerId(playerId);
        score.setEqualsGame(score.getEqualsGame() + TennisPoints.GAME.getValue());
        if (hasWinnerTieBreak()) {
            scoreOne.setGame(0);
            scoreTwo.setGame(0);
            scoreOne.setEqualsGame(0);
            scoreTwo.setEqualsGame(0);
            score.setSet(TennisPoints.SET.getValue());
        }
    }

    private boolean hasWinnerTieBreak() {
        int equalsGameOne = scoreOne.getEqualsGame();
        int equalsGameTwo = scoreTwo.getEqualsGame();
        int resultOne = equalsGameOne - equalsGameTwo;
        int resultTwo = equalsGameTwo - equalsGameOne;

        if (equalsGameOne >= 7 || equalsGameTwo >= 7) {
            if (resultOne >= 2 || resultTwo >= 2) {
                return true;
            }
        }
        return false;
    }
}
