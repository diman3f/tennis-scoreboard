package com.diman_3f.tennis_scoreboard.models;

public class RegularScore extends GameScore<RegularGamePlayerPoints> {

    @Override
    protected RegularGamePlayerPoints getZeroScore() {
        return RegularGamePlayerPoints.ZERO;
    }

    @Override
    protected State pointWon(int numberPlayer) {
        RegularGamePlayerPoints playerScore = getScorePlayer(numberPlayer);
        if (playerScore.ordinal() <= RegularGamePlayerPoints.THIRTY.ordinal()) {
            setScorePlayer(numberPlayer, RegularGamePlayerPoints.next(playerScore));
        } else if (playerScore == RegularGamePlayerPoints.FORTY) {
            if (getScoreOppositePlayer(numberPlayer) == RegularGamePlayerPoints.ADVANTAGE) {
                setScoreOppositePlayer(numberPlayer, RegularGamePlayerPoints.FORTY);
            } else if (getScoreOppositePlayer(numberPlayer) == RegularGamePlayerPoints.FORTY) {
                setScorePlayer(numberPlayer, RegularGamePlayerPoints.ADVANTAGE);
            } else {
                return numberPlayer == 0 ? State.PLAYER_WON_ONE : State.PLAYER_WON_TWO;
            }
        } else if (playerScore == RegularGamePlayerPoints.ADVANTAGE) {
            return numberPlayer == 0 ? State.PLAYER_WON_ONE : State.PLAYER_WON_TWO;
        } else {
            throw new IllegalStateException("pointWon() не вызывается на Advantage");
        }
        return State.ONGOING;
    }
}
