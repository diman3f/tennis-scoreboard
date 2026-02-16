package com.diman_3f.tennis_scoreboard.models;

import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
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
    private Player winner;
    private int setOnePlayer;
    private int setTwoPlayer;
    private int gameOnePlayer;
    private int gameTwoPlayer;
    private String pointOnePlayer;
    private String pointTwoPlayer;
    private boolean advantageOnePlayer;
    private boolean advantageTwoPlayer;
    private int advantageOnePoint;
    private int advantageTwoPoint;
    private Integer winnerPlayerId;
    private Game game;
    private SetMatch set;
    private TennisMatchState state;
    private MatchScoreModel scoreModel;
    private boolean matchFinished;


    public OngoingMatch(Player playerOne, Player playerTwo) {
        this.playerOneId = playerOne.getId();
        this.playerTwoId = playerTwo.getId();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.setOnePlayer = 0;
        this.gameOnePlayer = 0;
        this.pointOnePlayer = "0";
        this.setTwoPlayer = 0;
        this.gameTwoPlayer = 0;
        this.pointTwoPlayer = "0";
        this.advantageOnePlayer = false;
        this.advantageTwoPlayer = false;
        this.game = new Game();
        this.set = new SetMatch();
        this.state = TennisMatchState.REGULAR_STATE;
        this.scoreModel = new MatchScoreModel();
        this.matchFinished = false;
    }

    public OngoingMatch upPointPlayer(int numberPlayer) {
        State state = scoreModel.pointWon(numberPlayer);
        checkWinnerMatch(state);
        CurrentScore currentScore = scoreModel.getCurrentScore();
        setOnePlayer = currentScore.getSetOne();
        gameOnePlayer = currentScore.getGameOne();
        pointOnePlayer = currentScore.getPointOne();
        setTwoPlayer = currentScore.getSetTwo();
        gameTwoPlayer = currentScore.getGameTwo();
        pointTwoPlayer = currentScore.getPointTwo();
        return this;
    }

    private boolean checkWinnerMatch(State state) {
        if (state.equals(State.PLAYER_WON_ONE)) {
            this.winner = playerOne;
            this.matchFinished = true;
            return true;
        } else if (state.equals(State.PLAYER_WON_TWO)) {
            this.winner = playerTwo;
            this.matchFinished = true;
            return true;
        }
        return false;
    }

    public ScoreDto getScore() {
        ScoreDto dto = new ScoreDto(playerOneId, playerTwoId, playerOne.getName(), playerTwo.getName(), setOnePlayer,
                gameOnePlayer, pointOnePlayer, setTwoPlayer,
                gameTwoPlayer, pointTwoPlayer, false);
        return dto;
    }

    public boolean isMatchFinished() {
        return matchFinished;
    }

    public Player getWinner() {
        return winner;
    }

    public int getSetPlayer(int playerId) {
        if (playerId == playerOneId) {
            return setOnePlayer;
        } else {
            return setTwoPlayer;
        }
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

}


