package com.diman_3f.tennis_scoreboard.models;

import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.services.TennisMatchState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OngoingMatch {

    private int playerOneId;
    private int playerTwoId;
    private Player playerOne;
    private Player playerTwo;
    private int setOnePlayer;
    private int setTwoPlayer;
    private boolean advantageOnePlayer;
    private boolean advantageTwoPlayer;
    private int advantageOnePoint;
    private int advantageTwoPoint;
    private Integer winnerPlayerId;
    private Game game;
    private SetMatch set;
    private TennisMatchState state;

    public OngoingMatch(Player playerOne, Player playerTwo) {
        this.playerOneId = playerOne.getId();
        this.playerTwoId = playerTwo.getId();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.setOnePlayer = 0;
        this.setTwoPlayer = 0;
        this.advantageOnePlayer = false;
        this.advantageTwoPlayer = false;
        this.game = new Game();
        this.set = new SetMatch();
        this.state = TennisMatchState.REGULAR_STATE;
    }

    public void upPointAdvantageOne() {
        this.advantageOnePoint++;
    }

    public void upPointAdvantageTwo() {
        this.advantageTwoPoint++;
    }

    public void setMatchState(TennisMatchState state) {
        this.state = state;
    }

    public boolean isMatchFinished() {
        return state == TennisMatchState.FINISHED;
    }

    public Player getWinner() {
        if (winnerPlayerId == playerOneId) {
            return playerOne;
        }
        return playerTwo;
    }

    public int getSetPlayer(int playerId) {
        if (playerId == playerOneId) {
            return setOnePlayer;
        } else {
            return setTwoPlayer;
        }
    }

    public void setWinnerPlayerId(int playerId) {
        winnerPlayerId = playerId;
    }


    public int getPointTieBreak(int playerId) {
        if (playerId == playerOneId) {
            return set.getPointOneTieBreakPlayer();
        } else {
            return set.getPointTwoTieBreakPlayer();
        }
    }

    public Integer getPointPlayer(int playerId) {
        if (playerId == playerOneId) {
            return game.getPointOnePlayer();
        } else {
            return game.getPointTwoPlayer();
        }
    }

    public Integer getGamePlayer(int playerId) {
        if (playerId == playerOneId) {
            return set.getGameOnePlayer();
        } else {
            return set.getGameTwoPlayer();
        }
    }

    public void upPointOnePlayer() {
        game.setPointOnePlayer();
    }

    public void upPointTwoPlayer() {
        game.setPointTwoPlayer();
    }

    public void upGameOnePlayer() {
        set.upGameOnePlayer();
    }

    public void upGameTwoPlayer() {
        set.upGameTwoPlayer();
    }

    public void upSetOnePlayer() {
        this.setOnePlayer++;
    }

    public void upSetTwoPlayer() {
        this.setTwoPlayer++;
    }


}


