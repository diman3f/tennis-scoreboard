package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Класс для создания новых матчей для учета счета игры.
 * <p>
 * Функциональность:
 * Создает матч на основе переданных имен игроков из vue
 * Выдать матч по uuid
 * <p>
 * Взаимодействие с другими классами программы:
 * Контроллер получает доступ к активному матчу через uuid матча
 * Через контроллер передаем id игрока для начисления point
 */
public class MatchCreatorService {

    private PlayerDao playerDao;
    private Map<UUID, ActiveMatch> matches;


    public MatchCreatorService() {
        this.matches = new HashMap<>();
    }

    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public UUID createCurrentMatch(String namePlayer1, String namePlayer2) {

        int idPlayerOne = playerDao.findByName(namePlayer1).getId();
        int idPlayerTwo = playerDao.findByName(namePlayer2).getId();

        UUID uuid = UUID.randomUUID();
        ActiveMatch activeMatch = ActiveMatch.builder()
                .playerOneID(idPlayerOne) //todo варик заменить на имена, чтобы не делать лишние запросы сверху
                .playerTwoID(idPlayerTwo) //todo еще варик создать отдельный класс для инициализации ActiveMatch (OngoingMatch)
                .isActive(true)
                .isTieBreak(false)
                .isGameEquals(false)
                .build();
        matches.put(uuid, activeMatch);
        return uuid;
    }

    public ActiveMatch getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public void deleteMatchByUUID(UUID uuid) {
        matches.remove(uuid);
    }

}

