package com.diman_3f.tennis_scoreboard.models;


import com.diman_3f.tennis_scoreboard.services.TennisMatchState;
import lombok.*;

import java.util.*;


/**
 * Класс для хранения состояния текущего матча для двух игроков
 * Изменение состояния происходит через изменение ScorePlayer другими классами
 * <p>
 * Изменяемые части класса:
 * Не изменяемые части класса:
 * Ограничения:
 * команда из двух игроков
 * имя команды, а не имя игрока
 */

@Getter
@Setter

public class ActiveMatch {

    private int playerOneId;
    private int playerTwoId;
    private int setOnePlayer;
    private int setTwoPlayer;
    private boolean advantageOnePlayer;
    private boolean advantageTwoPlayer;
    private Integer winnerPlayerId;
    private Game game;
    private SetMatch set;
    private TennisMatchState state;


    public ActiveMatch(int playerOneId, int playerTwoId) {
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.setOnePlayer = 0;
        this.setTwoPlayer = 0;
        this.advantageOnePlayer = false;
        this.advantageTwoPlayer = false;
        this.game = new Game();
        this.set = new SetMatch();
        this.state = TennisMatchState.REGULAR_STATE;
    }

    public void setStateMatch(int onePoint, int oneGame, int oneSet, int twoPoint, int twoGame, int twoSet, TennisMatchState state) {
        for (int i = 0; i < onePoint; i++) {
            this.game.setPointOnePlayer();
        }
        for (int i = 0; i < twoPoint; i++) {
            this.game.setPointTwoPlayer();
        }
        for (int i = 0; i < oneGame; i++) {
            this.set.upGameOnePlayer();
        }
        for (int i = 0; i < twoGame; i++) {
            this.set.upGameTwoPlayer();
        }
        this.setOnePlayer = oneSet;
        this.setTwoPlayer = twoSet;
        this.state = state;
    }

    public void setMatchState(TennisMatchState state) {
        this.state = state;
    }

    public boolean isMatchFinished() {
        return state == TennisMatchState.FINISHED;
    }

    public int getIdPlayerWinnerMatch() {
        if (winnerPlayerId != null && state == TennisMatchState.FINISHED) {
            return winnerPlayerId;
        }
        throw new NoSuchElementException("Матч не закончен, победитель не определен");
    }

    public void setAdvantagePlayer(int playerId) {
        if (playerId == playerOneId) {
            this.advantageOnePlayer = true;
            this.advantageTwoPlayer = false;
        } else if (playerId == playerTwoId) {
            this.advantageTwoPlayer = true;
            this.advantageOnePlayer = false;
        }
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


    public boolean hasAdvantagePlayerById(int playerId) {
        if (playerId == playerOneId) {
            return game.isAdvantageOnePlayer();
        } else {
            return game.isAdvantageTwoPlayer();
        }
    }

    public int getPointTieBreak(int playerId) {
        if (playerId == playerOneId) {
            return set.getPointOnePlayer();
        } else {
            return set.getPointTwoPlayer();
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


    public Map<Integer, Integer> getGamePlayers() {
        Map<Integer, Integer> games = new HashMap<>();
        Integer gameOnePlayer = set.getGameOnePlayer();
        Integer gameTwoPlayer = set.getGameTwoPlayer();
        games.put(playerOneId, gameOnePlayer);
        games.put(playerTwoId, gameTwoPlayer);
        return games;
    }

}


