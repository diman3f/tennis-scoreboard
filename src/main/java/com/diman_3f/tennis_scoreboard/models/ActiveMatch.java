package com.diman_3f.tennis_scoreboard.models;


import lombok.*;

import java.util.UUID;


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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveMatch {

    private UUID id;
    private int playerOneID;
    private int playerTwoID;
    @Builder.Default
    private ScorePlayer scorePlayerOne = new ScorePlayer();
    @Builder.Default
    private ScorePlayer scorePlayerTwo = new ScorePlayer();


    public ScorePlayer getByPlayerId(int playerId) {
        if (playerId == playerOneID) {
            return scorePlayerOne;
        } else {
            return scorePlayerTwo;
        }
    }

}


