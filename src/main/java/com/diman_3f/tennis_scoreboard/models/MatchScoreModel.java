package com.diman_3f.tennis_scoreboard.models;

public class MatchScoreModel extends Score<Integer> {
    private static final int MIN_SET_FOR_WIN_MATCH = 2;
    private SetScore setScore;

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    public MatchScoreModel() {
        this.setScore = new SetScore();
    }

    @Override
    protected State pointWon(int numberPlayer) {
        State state = setScore.pointWon(numberPlayer);
        if (state == State.PLAYER_WON_ONE) {
            setScorePlayer(numberPlayer, getScorePlayer(numberPlayer) + 1);
            setScore = new SetScore();
            return wonMatch(numberPlayer);
        } else if (state == State.PLAYER_WON_TWO) {
            setScorePlayer(numberPlayer, getScorePlayer(numberPlayer) + 1);
            setScore = new SetScore();
            return wonMatch(numberPlayer);
        }
        return State.ONGOING;
    }

    private State wonMatch(int numberPlayer) {
        Integer scorePlayer = getScorePlayer(numberPlayer);
        if (scorePlayer == MIN_SET_FOR_WIN_MATCH) {
            return numberPlayer == 0 ? State.PLAYER_WON_ONE : State.PLAYER_WON_TWO;
        } else {
            return State.ONGOING;
        }
    }

    public CurrentScore getCurrentScore() {
        CurrentScore model = new CurrentScore();
        model.setSetOne(getScorePlayer(getINDEX_ONE_PLAYER()));
        model.setSetTwo(getScorePlayer(getINDEX_OPPOSITE_PLAYER()));
        return setScore.getCurrentScore(model);
    }
}