package com.diman_3f.tennis_scoreboard.models;

public class MatchScoreModel {
    private GameScore<?> gameScore;

    public MatchScoreModel() {
        this.gameScore = new RegularScore();
    }

    public ScoreModel pointWon(int numberPlayer) {

        gameScore.pointWon(numberPlayer);
        Object onePoint =  gameScore.getScorePlayer(0);
        Object twoPoint =  gameScore.getScoreOppositePlayer(0);
        ScoreModel model = new ScoreModel();
        if (twoPoint == RegularGamePlayerPoints.FIFTY) {
            model.setPointOne(0);
            model.setPointTwo(1);
        } else if (twoPoint == RegularGamePlayerPoints.THIRTY) {
            model.setPointOne(0);
            model.setPointTwo(2);
        }
        return model;
    }
}
