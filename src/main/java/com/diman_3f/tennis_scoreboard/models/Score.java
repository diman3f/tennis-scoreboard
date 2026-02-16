package com.diman_3f.tennis_scoreboard.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Score<T> {

    private final List<T> playerScore;
    private final int INDEX_ONE_PLAYER = 0;
    private final int INDEX_OPPOSITE_PLAYER = 1;

    protected abstract T getZeroScore();

    public Score() {
        this.playerScore = new ArrayList<>();
        playerScore.add(getZeroScore());
        playerScore.add(getZeroScore());
    }

    public T getScorePlayer(int playerNumber) {
        return playerScore.get(playerNumber);
    }

    public T getScoreOppositePlayer(int playerNumber) {
        return playerScore.get(playerNumber == INDEX_ONE_PLAYER ? INDEX_OPPOSITE_PLAYER : INDEX_ONE_PLAYER);
    }

    public void setScorePlayer(int playerNumber, T score) {
        playerScore.set(playerNumber, score);
    }

    public void setScoreOppositePlayer(int playerNumber, T score) {
        playerScore.set(playerNumber == INDEX_ONE_PLAYER ? INDEX_OPPOSITE_PLAYER : INDEX_ONE_PLAYER, score);
    }

    protected abstract State pointWon(int numberPlayer);
}
