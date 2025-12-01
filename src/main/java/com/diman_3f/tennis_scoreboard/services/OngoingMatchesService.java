package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

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
public class OngoingMatchesService {

    private PlayerDao playerDao;
    private Map<UUID, OngoingMatch> matches;


    public OngoingMatchesService() {
        this.playerDao = ServiceLocator.getService(PlayerDao.class);
        this.matches = new HashMap<>();
    }



    public UUID createCurrentMatch(String namePlayer1, String namePlayer2) {

        int idPlayerOne = playerDao.findByName(namePlayer1).getId();
        int idPlayerTwo = playerDao.findByName(namePlayer2).getId();

        UUID uuid = UUID.randomUUID();
        OngoingMatch ongoingMatch = new OngoingMatch(idPlayerOne, idPlayerTwo);
        matches.put(uuid, ongoingMatch);
        return uuid;
    }

    public OngoingMatch getMatch(String uuid) {
        return matches.get(UUID.fromString(uuid));
    }

    public void deleteMatchByUUID(UUID uuid) {
        matches.remove(uuid);
    }

}

