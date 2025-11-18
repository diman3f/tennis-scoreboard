package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.TennisPoints;
import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;

public class ScoreDtoFormatter {

    private ActiveMatch match;


    public ScoreDtoFormatter(ActiveMatch match) {
        this.match = match;
    }

    public ScoreDto createDto() {
        if (match.getState().equals(TennisMatchState.REGULAR_STATE)) {
            return getRegularScore();
        } else if (match.getState().equals(TennisMatchState.ADVANTAGE)) {
            return getAdvantageScore();
        } else if (match.getState().equals(TennisMatchState.TIEBREAK)) {
            return getTiebreak();
        } else if (match.getState().equals(TennisMatchState.FINISHED)) {
            return getRegularScore();
        } else throw new RuntimeException("Ошибка в определении статуса матча");
    }

    private ScoreDto getRegularScore() {
        int idPlayerOne = match.getPlayerOneId();
        int idPlayerTwo = match.getPlayerTwoId();
        int pointOne = match.getPointPlayer(idPlayerOne);
        int pointTwo = match.getPointPlayer(idPlayerTwo);

        return ScoreDto.builder()
                .playerOneID(idPlayerOne)
                .pointOne(getStringPoint(pointOne))
                .gameOne(match.getGamePlayer(idPlayerOne))
                .setOne(match.getSetOnePlayer())

                .playerTwoID(idPlayerTwo)
                .pointTwo(getStringPoint(pointTwo))
                .gameTwo(match.getGamePlayer(idPlayerTwo))
                .setTwo(match.getSetTwoPlayer())
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

    private ScoreDto getAdvantageScore() {
        ScoreDto dto = getRegularScore();
        setAdvantage(dto);
        return dto;
    }

    private void setAdvantage(ScoreDto dto) {
        if (match.isAdvantageOnePlayer()) {
            dto.setPointOne("AD");
        } else if (match.isAdvantageTwoPlayer()) {
            dto.setPointTwo("AD");
        }
    }

    private ScoreDto getTiebreak() {
        ScoreDto dto = getRegularScore();
        dto.setPointOne(String.valueOf(match.getSet().getPointOnePlayer()));
        dto.setPointTwo(String.valueOf(match.getSet().getPointTwoPlayer()));
        return dto;
    }

}
