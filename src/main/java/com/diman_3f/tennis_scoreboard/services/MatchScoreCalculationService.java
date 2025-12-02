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
            if (!tennisRuleHandler.isDeuce(match)) {
                upPointPlayerId(playerId, match);
                match.setMatchState(TennisMatchState.REGULAR_STATE);
                if (tennisRuleHandler.hasWinnerInGame(playerId, match)) {
                    actionResetDefaultFieldGame(match);
                    upGamePlayerById(playerId, match);
                    if (tennisRuleHandler.hasWinnerInSet(playerId, match)) {
                        actionResetDefaultFieldGame(match);
                        actionResetDefaultFieldSet(match.getSet());
                        upSetPlayerById(playerId, match);

                    }
                }
            } else {
                match.setMatchState(TennisMatchState.ADVANTAGE);
                upPointAdvantageById(playerId, match);
                checkAdvantagePlayer(playerId, match);
            }
        } else {
            match.setMatchState(TennisMatchState.TIEBREAK);
            upPointTieBreakPlayerById(playerId, match);
            if (tennisRuleHandler.hasWinnerInTieBreak(playerId, match)) {
                actionResetDefaultFieldGame(match);
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
        int pointADPlayer = match.getAdvantagePointById(playerId);
        int differentPoints = Math.abs(match.getAdvantageOnePoint() - match.getAdvantageTwoPoint());
        if (differentPoints == 2) {
            upGamePlayerById(playerId, match);
            actionResetDefaultFieldGame(match);
        } else if (differentPoints == 1) {
            actionResetDefaultFieldAdvantage(match);
            setAdvantagePlayer(playerId, match);
        }
    }


    private void actionResetDefaultFieldGame(OngoingMatch match) {
        match.getGame().resetFieldDefault();
        match.setAdvantageOnePlayer(false);
        match.setAdvantageTwoPlayer(false);

    }

    private void actionResetDefaultFieldSet(SetMatch set) {
        set.resetFieldDefault();
    }
    private void actionResetDefaultFieldAdvantage(OngoingMatch match) {
        match.setAdvantageOnePlayer(false);
        match.setAdvantageTwoPlayer(false);
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

    public void setAdvantagePlayer(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.setAdvantageOnePlayer(true);
        } else {
            match.setAdvantageTwoPlayer(true);
        }
    }


    public void upPointTieBreakPlayerById(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.getSet().upPointTieBreakOnePlayer();
        } else {
            match.getSet().upPointTieBreakTwoPlayer();
        }
    }


    private void upPointAdvantageById(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.setAdvantageOnePoint();
        } else {
            match.setAdvantageTwoPoint();
        }
    }
}
