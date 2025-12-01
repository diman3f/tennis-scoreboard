package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisRuleThreshold;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

import java.util.Map;

public class TennisRuleHandler {



    public boolean isDeuce(OngoingMatch match) {
        int pointOnePlayer = match.getPointPlayer(match.getPlayerOneId());
        int pointTwoPlayer = match.getPointPlayer(match.getPlayerTwoId());
        return pointOnePlayer == TennisRuleThreshold.MIN_POINT_TO_DONE_EQUALS_GAME.getValue() &&
                pointTwoPlayer == TennisRuleThreshold.MIN_POINT_TO_DONE_EQUALS_GAME.getValue();
    }

    public boolean hasWinnerInGame(int playerId, OngoingMatch match) {
        return hasMinPointOnScoreToWinGame(playerId, match) && isRuleByDifferentPointDone(match);
    }

    private boolean hasMinPointOnScoreToWinGame(int playerId, OngoingMatch match) {
        return match.getPointPlayer(playerId) == TennisRuleThreshold.MIN_POINT_TO_WIN_GAME.getValue();
    }

    private boolean isRuleByDifferentPointDone(OngoingMatch match) {
        int pointOnePlayer = match.getPointPlayer(match.getPlayerOneId());
        int pointTwoPlayer = match.getPointPlayer(match.getPlayerTwoId());
        return Math.abs(pointOnePlayer - pointTwoPlayer) >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_GAME.getValue();
    }

    public boolean hasWinnerInSet(int playerId, OngoingMatch match) {
        return hasMinGameOnScoreToWinSet(playerId,match) & isRuleByDifferentGameDone(match);
    }

    private boolean hasMinGameOnScoreToWinSet(int playerId,OngoingMatch match) {
        Map<Integer, Integer> gamePlayers = match.getGamePlayers();
        return gamePlayers.get(playerId) >= TennisRuleThreshold.MIN_GAME_TO_WIN_SET.getValue();
    }

    private boolean isRuleByDifferentGameDone(OngoingMatch match) {
        int gameOnePlayer = match.getGamePlayers().get(match.getPlayerOneId());
        int gameTwoPlayer = match.getGamePlayers().get(match.getPlayerTwoId());
        return Math.abs(gameOnePlayer - gameTwoPlayer) > TennisRuleThreshold.MIN_DIFFERENT_GAME_TO_WIN_SET.getValue();
    }

    public boolean isTieBreak(OngoingMatch match) {
        return match.getGamePlayers().get(match.getPlayerOneId()) == TennisRuleThreshold.MIN_GAME_TO_DONE_TIE_BREAK.getValue() &&
                match.getGamePlayers().get(match.getPlayerOneId()) == TennisRuleThreshold.MIN_GAME_TO_DONE_TIE_BREAK.getValue();

    }

    public boolean hasWinnerInTieBreak(int playerId, OngoingMatch match) {
        return hasMinPointTieBreakToWinTieBreak(playerId, match) && isRuleByDifferentPointTieBreakDone(match);
    }

    private boolean hasMinPointTieBreakToWinTieBreak(int playerId, OngoingMatch match) {
        return match.getPointTieBreak(playerId) >= TennisRuleThreshold.MIN_POINT_TO_WIN_TIE_BREAK.getValue();
    }

    private boolean isRuleByDifferentPointTieBreakDone(OngoingMatch match) {
        return Math.abs(match.getSet().getPointOnePlayer() - match.getSet().getPointTwoPlayer()) >=
                TennisRuleThreshold.MIN_DIFFERENT_POINT_TIE_BREAK_TO_WIN_TIE_BREAK.getValue();
    }

    public boolean hasWinnerInMatch(int playerId, OngoingMatch match) {
       return hasMinSetOnScoreToWinMatch(playerId, match) && isRuleByDifferentSetDone(match);
    }

    private boolean hasMinSetOnScoreToWinMatch(int playerId, OngoingMatch match) {
        int setPlayer = match.getSetPlayer(playerId);
        return setPlayer == TennisRuleThreshold.MIN_SET_TO_WIN_MATCH.getValue();

    }

    private boolean isRuleByDifferentSetDone(OngoingMatch match) {
        int setOnePlayer = match.getSetOnePlayer();
        int setTwoPlayer = match.getSetTwoPlayer();
        return Math.abs(setOnePlayer - setTwoPlayer) >= TennisRuleThreshold.MIN_DIFFERENT_POINT_TO_WIN_MATCH.getValue();
    }

}
