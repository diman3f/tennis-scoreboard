package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisPoints;
import com.diman_3f.tennis_scoreboard.TennisRuleThreshold;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.models.ScorePlayer;

import java.util.Map;

public class TennisRuleHandler {
    private ActiveMatch match;


    public TennisRuleHandler(ActiveMatch match) {
        this.match = match;
    }

    public boolean isDeuce() {
        int pointOnePlayer = match.getPointPlayer(match.getPlayerOneId());
        int pointTwoPlayer = match.getPointPlayer(match.getPlayerTwoId());
        return pointOnePlayer == TennisRuleThreshold.MIN_POINT_TO_DONE_EQUALS_GAME.getValue() &&
                pointTwoPlayer == TennisRuleThreshold.MIN_POINT_TO_DONE_EQUALS_GAME.getValue();
    }

    public boolean hasWinnerInGame(int playerId) {
        return hasMinPointOnScoreToWinGame(playerId) && isRuleByDifferentPointDone();
    }

    private boolean hasMinPointOnScoreToWinGame(int playerId) {
        return match.getPointPlayer(playerId) == TennisRuleThreshold.MIN_POINT_TO_WIN_GAME.getValue();
    }

    private boolean isRuleByDifferentPointDone() {
        int pointOnePlayer = match.getPointPlayer(match.getPlayerOneId());
        int pointTwoPlayer = match.getPointPlayer(match.getPlayerTwoId());
        return Math.abs(pointOnePlayer - pointTwoPlayer) >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_GAME.getValue();
    }

    public boolean hasWinnerInSet(int playerId) {
        return hasMinGameOnScoreToWinSet(playerId) & isRuleByDifferentGameDone();
    }

    private boolean hasMinGameOnScoreToWinSet(int playerId) {
        Map<Integer, Integer> gamePlayers = match.getGamePlayers();
        return gamePlayers.get(playerId) >= TennisRuleThreshold.MIN_GAME_TO_WIN_SET.getValue();
    }

    private boolean isRuleByDifferentGameDone() {
        int gameOnePlayer = match.getGamePlayers().get(match.getPlayerOneId());
        int gameTwoPlayer = match.getGamePlayers().get(match.getPlayerTwoId());
        return Math.abs(gameOnePlayer - gameTwoPlayer) > TennisRuleThreshold.MIN_DIFFERENT_GAME_TO_WIN_SET.getValue();
    }

    public boolean isTieBreak() {
        return match.getGamePlayers().get(match.getPlayerOneId()) == TennisRuleThreshold.MIN_GAME_TO_DONE_TIE_BREAK.getValue() &&
                match.getGamePlayers().get(match.getPlayerOneId()) == TennisRuleThreshold.MIN_GAME_TO_DONE_TIE_BREAK.getValue();

    }

    public boolean hasWinnerInTieBreak(int playerId) {
        return hasMinPointTieBreakToWinTieBreak(playerId) && isRuleByDifferentPointTieBreakDone();
    }

    private boolean hasMinPointTieBreakToWinTieBreak(int playerId) {
        return match.getPointTieBreak(playerId) >= TennisRuleThreshold.MIN_POINT_TO_WIN_TIE_BREAK.getValue();
    }

    private boolean isRuleByDifferentPointTieBreakDone() {
        return Math.abs(match.getSet().getPointOnePlayer() - match.getSet().getPointTwoPlayer()) >=
                TennisRuleThreshold.MIN_DIFFERENT_POINT_TIE_BREAK_TO_WIN_TIE_BREAK.getValue();
    }

    public boolean hasWinnerInMatch(int playerId) {
       return hasMinSetOnScoreToWinMatch(playerId) && isRuleByDifferentSetDone();
    }

    private boolean hasMinSetOnScoreToWinMatch(int playerId) {
        int setPlayer = match.getSetPlayer(playerId);
        return setPlayer == TennisRuleThreshold.MIN_SET_TO_WIN_MATCH.getValue();

    }

    private boolean isRuleByDifferentSetDone() {
        int setOnePlayer = match.getSetOnePlayer();
        int setTwoPlayer = match.getSetTwoPlayer();
        return Math.abs(setOnePlayer - setTwoPlayer) >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_MATCH.getValue();
    }

}
