package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import com.diman_3f.tennis_scoreboard.models.Game;
import com.diman_3f.tennis_scoreboard.models.SetMatch;

import java.util.Map;


/**
 * класс для изменения полей счета ScorePlayer в текущем матче
 * после изменения полей должен отдать состояние текущего матча
 * 16.10.25 класс логического счета матча изменяет состояние текущего матча
 */

public class MatchScoreCalculationService {

    private TennisRuleHandler tennisRuleHandler;


    public MatchScoreCalculationService(TennisRuleHandler handler) {
        this.tennisRuleHandler = handler;
    }


    public OngoingMatch upPoint(int playerId, OngoingMatch match) {
        if (!tennisRuleHandler.isTieBreak(match)) {
            if (tennisRuleHandler.isDeuce(match)) {
                match.setMatchState(TennisMatchState.ADVANTAGE);
                checkAdvantagePlayer(playerId, match);

            } else {
                upPointPlayerId(playerId, match);
                match.setMatchState(TennisMatchState.REGULAR_STATE);
                if (tennisRuleHandler.hasWinnerInGame(playerId, match)) {
                    actionResetDefaultFieldGame(match.getGame());
                    upGamePlayerById(playerId, match);
                    if (tennisRuleHandler.hasWinnerInSet(playerId, match)) {
                        actionResetDefaultFieldGame(match.getGame());
                        actionResetDefaultFieldSet(match.getSet());
                        upSetPlayerById(playerId, match);
                    }
                }
            }
        } else {
            match.setMatchState(TennisMatchState.TIEBREAK);
            upPointTieBreakPlayerById(playerId, match);
            if (tennisRuleHandler.hasWinnerInTieBreak(playerId, match)) {
                actionResetDefaultFieldGame(match.getGame());
                actionResetDefaultFieldSet(match.getSet());
                upSetPlayerById(playerId, match);
                match.setMatchState(TennisMatchState.REGULAR_STATE);
            }

        }
        if (tennisRuleHandler.hasWinnerInMatch(playerId, match)) {
            match.setMatchState(TennisMatchState.FINISHED);
            match.setWinnerPlayerId(playerId);
        }
        return match;
    }


    private void checkAdvantagePlayer(int playerId, OngoingMatch match) {
        Map<Integer, Integer> gamePlayers = match.getGamePlayers();
        if (match.hasAdvantagePlayerById(playerId)) {
            actionResetDefaultFieldGame(match.getGame());
            if (gamePlayers.get(playerId) == match.getPlayerOneId()) {
                match.upGameOnePlayer();
            } else {
                match.upGameTwoPlayer();
            }
        } else {
            match.setAdvantagePlayer(playerId);
        }
    }

    private void actionResetDefaultFieldGame(Game game) {
        game.resetFieldDefault();
    }

    private void actionResetDefaultFieldSet(SetMatch set) {
        set.resetFieldDefault();
    }


    private void upPointPlayerId(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.upPointOnePlayer();
        } else {
            match.upPointTwoPlayer();
        }
    }

    public void upSetPlayerById(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.upSetOnePlayer();
        } else {
            match.upSetTwoPlayer();
        }
    }

    public void upGamePlayerById(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.upGameOnePlayer();
        } else {
            match.upGameTwoPlayer();
        }
    }

    public void upPointTieBreakPlayerById(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.getSet().upPointTieBreakOnePlayer();
        } else {
            match.getSet().upPointTieBreakTwoPlayer();
        }
    }

}
