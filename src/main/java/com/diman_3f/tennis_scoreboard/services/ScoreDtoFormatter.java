package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisPoints;
import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

public class ScoreDtoFormatter {


    public ScoreDto createDto(OngoingMatch match) {
        if (match.getState().equals(TennisMatchState.REGULAR_STATE)) {
            return getRegularScore(match);
        } else if (match.getState().equals(TennisMatchState.ADVANTAGE)) {
            return getAdvantageScore(match);
        } else if (match.getState().equals(TennisMatchState.TIEBREAK)) {
            return getTiebreak(match);
        } else if (match.getState().equals(TennisMatchState.FINISHED)) {
            return getFinishedMatch(match);
        } else throw new RuntimeException("Ошибка в определении статуса матча");
    }

    private ScoreDto getRegularScore(OngoingMatch match) {
        int idPlayerOne = match.getPlayerOneId();
        int idPlayerTwo = match.getPlayerTwoId();
        int pointOne = match.getPointPlayer(idPlayerOne);
        int pointTwo = match.getPointPlayer(idPlayerTwo);

        return ScoreDto.builder()
                .playerOneId(idPlayerOne)
                .pointOne(getStringPoint(pointOne))
                .gameOne(match.getGamePlayer(idPlayerOne))
                .setOne(match.getSetOnePlayer())

                .playerTwoId(idPlayerTwo)
                .pointTwo(getStringPoint(pointTwo))
                .gameTwo(match.getGamePlayer(idPlayerTwo))
                .setTwo(match.getSetTwoPlayer())
                .page("match-score_jsp")
                .build();
    }

    private String getStringPoint(int point) {
        switch (point) {
            case 0:
                return String.valueOf(TennisPoints.ZERO.getValue());
            case 1:
                return String.valueOf(TennisPoints.ONE_POINT.getValue());
            case 2:
                return String.valueOf(TennisPoints.TWO_POINT.getValue());
            case 3:
                return String.valueOf(TennisPoints.THREE_POINT.getValue());
            default:
                throw new IllegalArgumentException("");
        }
    }

    private ScoreDto getAdvantageScore(OngoingMatch match) {
        ScoreDto dto = getRegularScore(match);
        setAdvantage(dto, match);
        return dto;
    }

    private void setAdvantage(ScoreDto dto, OngoingMatch match) {
        if (match.isAdvantageOnePlayer()) {
            dto.setPointOne("AD");
        } else if (match.isAdvantageTwoPlayer()) {
            dto.setPointTwo("AD");
        }
    }

    private ScoreDto getTiebreak(OngoingMatch match) {
        ScoreDto dto = getRegularScore(match);
        dto.setPointOne(String.valueOf(match.getSet().getPointOnePlayer()));
        dto.setPointTwo(String.valueOf(match.getSet().getPointTwoPlayer()));
        return dto;
    }
    private ScoreDto getFinishedMatch(OngoingMatch match) {
        ScoreDto dto = getRegularScore(match);
        dto.setFinishedMatch(match.isMatchFinished());
        dto.setIdPlayerOneWinner(match.getWinnerPlayerId());
        dto.setIdPlayerTwoWinner(match.getWinnerPlayerId());
        dto.setPage("match-score_finished");
        return dto;
    }

}
