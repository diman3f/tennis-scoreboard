package com.diman_3f.tennis_scoreboard.models;

public class TieBreakScore extends GameScore<Integer> {
    private static final int MIN_POINT_FOR_WIN_TIEBREAK = 7;
    private static final int DIFFERENT_POINT_FOR_WIN_TIEBREAK = 2;

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    protected State pointWon(int numberPlayer) {
        setScorePlayer(numberPlayer, getScorePlayer(numberPlayer) + 1);
        if (getScorePlayer(numberPlayer) >= MIN_POINT_FOR_WIN_TIEBREAK) {
            if (getScorePlayer(numberPlayer)  - getScoreOppositePlayer(numberPlayer) >= DIFFERENT_POINT_FOR_WIN_TIEBREAK) {
                System.out.println("победа в тайбрейке!");
                return numberPlayer == 0 ? State.PLAYER_WON_ONE : State.PLAYER_WON_TWO;
            }
        }
        return State.ONGOING;
    }
}
