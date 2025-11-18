package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.models.Game;
import com.diman_3f.tennis_scoreboard.models.SetMatch;

import java.util.Map;


/**
 * класс для изменения полей счета ScorePlayer в текущем матче
 * после изменения полей должен отдать состояние текущего матча
 * 16.10.25 класс логического счета матча изменяет состояние текущего матча
 */

public class MatchScoreCalculationService {

    private ActiveMatch match;
    private TennisRuleHandler tennisRuleHandler;
    private ScoreDtoFormatter dtoFormatter;

    public MatchScoreCalculationService(ActiveMatch match) {
        this.match = match;
        this.tennisRuleHandler = new TennisRuleHandler(match);
        this.dtoFormatter = new ScoreDtoFormatter(match);
    }

    public ActiveMatch getMatch() {
        return match;
    }


    public ScoreDto upPoint(int playerId) {

        if (!tennisRuleHandler.isTieBreak()) {
            if (tennisRuleHandler.isDeuce()) {
                match.setMatchState(TennisMatchState.ADVANTAGE);
                checkAdvantage(playerId);
            } else {
                upPointPlayerId(playerId);
                match.setMatchState(TennisMatchState.REGULAR_STATE);
                if (tennisRuleHandler.hasWinnerInGame(playerId)) {
                    actionResetDefaultFieldGame(match.getGame());
                    upGamePlayerById(playerId);
                    if (tennisRuleHandler.hasWinnerInSet(playerId)) {
                        actionResetDefaultFieldGame(match.getGame());
                        actionResetDefaultFieldSet(match.getSet());
                        upSetPlayerById(playerId);
                    }
                }
            }
        } else {
            match.setMatchState(TennisMatchState.TIEBREAK);
            upPointTieBreakPlayerById(playerId);
            if (tennisRuleHandler.hasWinnerInTieBreak(playerId)) {
                actionResetDefaultFieldGame(match.getGame());
                actionResetDefaultFieldSet(match.getSet());
                upSetPlayerById(playerId);
                match.setMatchState(TennisMatchState.REGULAR_STATE);
            }

        }
        if (tennisRuleHandler.hasWinnerInMatch(playerId)) {
            match.setMatchState(TennisMatchState.FINISHED);
            match.setWinnerPlayerId(playerId);
        }
        return dtoFormatter.createDto();
    }


    private void checkAdvantage(int playerId) {
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


    private void upPointPlayerId(int playerId) {
        if (playerId == match.getPlayerOneId()) {
            match.upPointOnePlayer();
        } else {
            match.upPointTwoPlayer();
        }
    }

    public void upSetPlayerById(int playerId) {
        if (playerId == match.getPlayerOneId()) {
            match.upSetOnePlayer();
        } else {
            match.upSetTwoPlayer();
        }
    }

    public void upGamePlayerById(int playerId) {
        if (playerId == match.getPlayerOneId()) {
            match.upGameOnePlayer();
        } else {
            match.upGameTwoPlayer();
        }
    }

    public void upPointTieBreakPlayerById(int playerId) {
        if (playerId == match.getPlayerOneId()) {
            match.getSet().upPointTieBreakOnePlayer();
        } else {
            match.getSet().upPointTieBreakTwoPlayer();
        }
    }

}
