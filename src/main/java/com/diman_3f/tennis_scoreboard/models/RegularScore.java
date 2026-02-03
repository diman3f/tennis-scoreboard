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
            setScorePlayer(numberPlayer, RegularGamePlayerPoints.next(playerScore)); // добавить очко игроку
        } else if (playerScore == RegularGamePlayerPoints.FORTY) { // если счет игрока равен 40
            if (getScoreOppositePlayer(numberPlayer) == RegularGamePlayerPoints.ADVANTAGE) { // если счет противоположного адвентедж
                setScoreOppositePlayer(numberPlayer, RegularGamePlayerPoints.FORTY); // снять адвентедж
            } else if (getScoreOppositePlayer(numberPlayer) == RegularGamePlayerPoints.FORTY) { //если счет счет противоположного 40
                setScorePlayer(numberPlayer, RegularGamePlayerPoints.ADVANTAGE);// установить текущему адвентедж
            } else {
                return numberPlayer == 0 ? State.WON_ONE : State.WON_TWO;
            }
        } else if (playerScore == RegularGamePlayerPoints.ADVANTAGE) {
            return numberPlayer == 0 ? State.WON_ONE : State.WON_TWO;
        } else {
            throw new IllegalStateException("pointWon() не вызывается на Advantage");
        }
        return State.ONGOING;
    }
}
