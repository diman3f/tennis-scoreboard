package com.diman_3f.tennis_scoreboard.service;

import com.diman_3f.tennis_scoreboard.model.ActiveMatch;
import com.diman_3f.tennis_scoreboard.model.ScorePlayer;

public class PointScoreCalculator {
    private ActiveMatch activeMatch;
    private final int ONE_POINT = 1;
    private final int ONE_GAME = 1;
    private ScorePlayer scoreOne;
    private ScorePlayer scoreTwo;

    public PointScoreCalculator(ActiveMatch activeMatch) {
        this.activeMatch = activeMatch;
        this.scoreOne = activeMatch.getScorePlayerOne();
        this.scoreTwo = activeMatch.getScorePlayerTwo();
    }

    public void upPointEqualsGame(Long playerId) {
        ScorePlayer score = activeMatch.getByPlayerId(playerId);
        score.setEqualsGame(score.getEqualsGame() + ONE_POINT);
        if (isPlayerWinInGameEquals()) {
            scoreOne.setPoint(0);
            scoreOne.setEqualsGame(0);
            scoreTwo.setPoint(0);
            scoreTwo.setEqualsGame(0);
            score.setGame(1);
        }
    }

    private boolean isPlayerWinInGameEquals() {
        int equalsGameOne = scoreOne.getEqualsGame();
        int equalsGameTwo = scoreTwo.getEqualsGame();
        int resultOne = equalsGameOne - equalsGameTwo;
        int resultTwo = equalsGameTwo - equalsGameOne;

        return hasWinnerGame(resultOne, resultTwo);
    }

    public void upGameEqualsSet(Long playerId) {
        ScorePlayer score = activeMatch.getByPlayerId(playerId);
        score.setEqualsGame(score.getEqualsGame() + ONE_GAME);
        if (isPlayerWinInSetEquals()) {
            scoreOne.setGame(0);
            scoreTwo.setGame(0);
            scoreOne.setEqualsGame(0);
            scoreTwo.setEqualsGame(0);
            score.setSet(1);
        }
    }

    private boolean isPlayerWinInSetEquals() {

        int equalsGameOne = scoreOne.getEqualsGame();
        int equalsGameTwo = scoreTwo.getEqualsGame();

        return hasWinnerSet(equalsGameOne, equalsGameTwo);
    }

    private boolean hasWinnerGame(int equalsGameOne, int equalsGameTwo) {
        int resultOne = equalsGameOne - equalsGameTwo;
        int resultTwo = equalsGameTwo - equalsGameOne;
        if (resultOne >= 2 || resultTwo >= 2) {
            return true;
        }
        return false;
    }


    private boolean hasWinnerSet(int equalsGameOne, int equalsGameTwo) {
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
