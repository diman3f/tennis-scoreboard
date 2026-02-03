package com.diman_3f.tennis_scoreboard.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> { //абстрактный счет который представлен парой значений

    private final List<T> playerScore;  // для хранение пары счета 0 - одна сторона 1 - противоположная сторона

    // получить нулевое значение счета
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
        return playerScore.get(playerNumber == 0 ? 1 : 0);
    }

    public void setScorePlayer(int playerNumber, T score) {
        playerScore.set(playerNumber, score);
    }

    public void setScoreOppositePlayer(int playerNumber, T score) {
        playerScore.set(playerNumber == 0 ? 1 : 0, score);
    }

    protected abstract State pointWon(int numberPlayer);

}
