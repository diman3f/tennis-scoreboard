package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisRuleThreshold;
import com.diman_3f.tennis_scoreboard.models.MatchScoreModel;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import com.diman_3f.tennis_scoreboard.models.ScoreModel;
import com.diman_3f.tennis_scoreboard.models.SetMatch;

public class MatchScoreCalculationService {

    private TennisRuleHandler tennisRuleHandler;
    private MatchScoreModel scoreModel;




    public MatchScoreCalculationService(TennisRuleHandler handler) {
        this.tennisRuleHandler = handler;
        this.scoreModel = new MatchScoreModel();
    }

    public OngoingMatch upPoint(int playerId, OngoingMatch match, int cap) {
        ScoreModel score = scoreModel.pointWon(playerId);
        match.getGame().setPointOne(score.getPointOne());
        match.getGame().setPointTwoPlayer(score.getPointTwo());
        return match;
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
        int differentPoints = Math.abs(match.getAdvantageOnePoint() - match.getAdvantageTwoPoint());
        if (differentPoints == TennisRuleThreshold.MIN_POINT_TO_WIN_ADVANTAGE.getValue()) {
            upGamePlayerById(playerId, match);
            resetAdvantagePoints(match);
            actionResetDefaultFieldGame(match);
            match.setMatchState(TennisMatchState.REGULAR_STATE);
        } else if (differentPoints == TennisRuleThreshold.MIN_DIFFERENT_POINT_ADVANTAGE_PLAYER.getValue()) {
            resetAdvantageExceptAdvantageById(playerId, match);
            setAdvantagePlayer(playerId, match);
        } else if (differentPoints == TennisRuleThreshold.DIFFERENT_POINT_DEUCE_STATE.getValue()) {
            resetAdvantageFields(match);
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

    private void resetAdvantageExceptAdvantageById(int playerId, OngoingMatch match) {
        match.setAdvantageOnePlayer(false);
        match.setAdvantageTwoPlayer(false);
        if (playerId == match.getPlayerOneId()) {
            match.setAdvantageOnePlayer(true);
        } else {
            match.setAdvantageTwoPlayer(true);
        }
    }

    private void resetAdvantagePoints(OngoingMatch match) {
        match.setAdvantageOnePoint(0);
        match.setAdvantageTwoPoint(0);
    }


    private void resetAdvantageFields(OngoingMatch match) {
        resetAdvantagePoints(match);
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

    private void upSetPlayerById(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.upSetOnePlayer();
        } else {
            match.upSetTwoPlayer();
        }
    }

    private void upGamePlayerById(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.upGameOnePlayer();
        } else {
            match.upGameTwoPlayer();
        }
    }

    private void setAdvantagePlayer(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.setAdvantageOnePlayer(true);
        } else {
            match.setAdvantageTwoPlayer(true);
        }
    }

    private void upPointTieBreakPlayerById(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.getSet().upPointTieBreakOnePlayer();
        } else {
            match.getSet().upPointTieBreakTwoPlayer();
        }
    }

    private void upPointAdvantageById(int playerId, OngoingMatch match) {
        if (playerId == match.getPlayerOneId()) {
            match.upPointAdvantageOne();
        } else {
            match.upPointAdvantageTwo();
        }
    }
}
