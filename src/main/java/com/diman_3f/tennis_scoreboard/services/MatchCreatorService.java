package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.models.ScorePlayer;

import java.util.*;

/**
 Класс для создания новых матчей для учета счета игры.

 Функциональность:
 Создает матч на основе переданных имен игроков из vue
 Выдать матч по uuid

 Взаимодействие с другими классами программы:
 Контроллер получает доступ к активному матчу через uuid матча
 Через контроллер передаем id игрока для начисления point

 */
public class MatchCreatorService {

    private PlayerDao playerDao;
    private Map<UUID, ActiveMatch> matches;
    private UUID uuid;


    public MatchCreatorService(PlayerDao playerDao) {
        this.playerDao = playerDao;
        this.matches = new HashMap<>();
    }

    public void createCurrentMatch(String namePlayer1, String namePlayer2) {

        Player playerOne = playerDao.findByName(namePlayer1);
        Player playerTwo = playerDao.findByName(namePlayer2);

            UUID uuid = UUID.randomUUID();
            this.uuid = uuid;
            ActiveMatch activeMatch = ActiveMatch.builder()
                    .id(uuid)
                    .playerOneID(playerOne.getId())
                    .playerTwoID(playerTwo.getId())
                    .build();
            activeMatch.setScorePlayerOne(new ScorePlayer());
            activeMatch.setScorePlayerTwo(new ScorePlayer());
            matches.put(uuid, activeMatch);
        }

    public ActiveMatch getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }
}

