package com.diman_3f.tennis_scoreboard.models;

import java.util.ArrayList;
import java.util.List;

public class SetScore extends Score<Integer> {
    private GameScore<?> gameScore;
    private final static int MIN_DIFFERENT_GAME_WIN_SET = 2;
    private final static int MIN_GAME_WIN_SET = 6;
    private final static int GAME_FOR_TIEBREAK = 6;

    public SetScore() {
        this.gameScore = new RegularScore();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    protected State pointWon(int numberPlayer) {
        State state = gameScore.pointWon(numberPlayer);
        if (gameScore instanceof TieBreakScore) {
            if (state == State.PLAYER_WON_ONE) {
                gameScore = new RegularScore();
                return State.PLAYER_WON_ONE;
            } else if (state == State.PLAYER_WON_TWO) {
                gameScore = new RegularScore();
                return State.PLAYER_WON_ONE;
            }
            return State.ONGOING;
        } else if (state == State.PLAYER_WON_ONE) {
            gameScore = new RegularScore();
            setScorePlayer(numberPlayer, getScorePlayer(numberPlayer) + 1);
            return gameWon(numberPlayer);
        } else if (state == State.PLAYER_WON_TWO) {
            gameScore = new RegularScore();
            setScorePlayer(numberPlayer, getScorePlayer(numberPlayer) + 1);
            return gameWon(numberPlayer);
        }
        return State.ONGOING;
    }

    private State gameWon(int playerNumber) {
        Integer scorePlayer = getScorePlayer(playerNumber);
        Integer scoreOppositePlayer = getScoreOppositePlayer(playerNumber);
        if (scorePlayer >= MIN_GAME_WIN_SET || scoreOppositePlayer >= MIN_GAME_WIN_SET) {
            if (scorePlayer - scoreOppositePlayer >= MIN_DIFFERENT_GAME_WIN_SET) {
                return State.PLAYER_WON_ONE;
            } else if (scoreOppositePlayer - scorePlayer >= MIN_DIFFERENT_GAME_WIN_SET) {
                return State.PLAYER_WON_TWO;
            } else if (scorePlayer == GAME_FOR_TIEBREAK && scoreOppositePlayer == GAME_FOR_TIEBREAK) {
                this.gameScore = new TieBreakScore();
            }
        }
        return State.ONGOING;
    }

    public CurrentScore getCurrentScore(CurrentScore model) {
        List<String> score = getPointsScore();
        model.setGameOne(getScorePlayer(getINDEX_ONE_PLAYER()));
        model.setGameTwo(getScorePlayer(getINDEX_OPPOSITE_PLAYER()));
        model.setPointOne(score.get(getINDEX_ONE_PLAYER()));
        model.setPointTwo(score.get(getINDEX_OPPOSITE_PLAYER()));
        return model;
    }

    private List<String> getPointsScore() {
        List<String> points = new ArrayList<>();
        if (gameScore instanceof RegularScore) {
            RegularScore score = (RegularScore) gameScore;
            points.add(getINDEX_ONE_PLAYER(), (score.getScorePlayer(getINDEX_ONE_PLAYER()).getNumber()));
            points.add(getINDEX_OPPOSITE_PLAYER(), (score.getScorePlayer(getINDEX_OPPOSITE_PLAYER()).getNumber()));
        } else if (gameScore instanceof TieBreakScore) {
            TieBreakScore score = (TieBreakScore) gameScore;
            points.add(getINDEX_ONE_PLAYER(), String.valueOf(score.getScorePlayer((getINDEX_ONE_PLAYER()))));
            points.add(getINDEX_OPPOSITE_PLAYER(), String.valueOf(score.getScorePlayer((getINDEX_OPPOSITE_PLAYER()))));
        } else {
            points.add(getINDEX_ONE_PLAYER(), String.valueOf(gameScore.getScorePlayer(getINDEX_ONE_PLAYER())));
            points.add(getINDEX_OPPOSITE_PLAYER(), String.valueOf(gameScore.getScorePlayer(getINDEX_OPPOSITE_PLAYER())));
        }
        return points;
    }
}
