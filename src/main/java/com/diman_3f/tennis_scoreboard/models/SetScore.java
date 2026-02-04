package com.diman_3f.tennis_scoreboard.models;

public class SetScore extends Score<Integer> {

    private GameScore<?> gameScore;// для получения информации о выйгранном гейме
    // если его иницаилизировать?
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
        if (state == State.WON_ONE) {
            gameScore = new RegularScore();
            setScorePlayer(numberPlayer, getScorePlayer(numberPlayer) + 1);
            return gameWon(numberPlayer);
        } else if (state == State.WON_TWO) {
            gameScore = new RegularScore();
            setScoreOppositePlayer(numberPlayer, getScoreOppositePlayer(numberPlayer) + 1);
            return gameWon(numberPlayer);
        }
        return State.ONGOING;
    }

    private State gameWon(int playerNumber) {
        Integer scorePlayer = getScorePlayer(playerNumber);
        Integer scoreOppositePlayer = getScoreOppositePlayer(playerNumber);
        if (scorePlayer >= MIN_GAME_WIN_SET || scoreOppositePlayer >= MIN_GAME_WIN_SET) {
            if (scorePlayer - scoreOppositePlayer >= MIN_DIFFERENT_GAME_WIN_SET) {
                return State.WON_ONE;
            } else if (scoreOppositePlayer - scorePlayer >= MIN_DIFFERENT_GAME_WIN_SET) {

                return State.WON_TWO;
            } else if (scorePlayer == GAME_FOR_TIEBREAK && scoreOppositePlayer == GAME_FOR_TIEBREAK) {
                //если победа в тайбрейке вернуть стайте и обнулить пересоздать тайбрек
            }
        }
        return State.ONGOING;
    }
}
