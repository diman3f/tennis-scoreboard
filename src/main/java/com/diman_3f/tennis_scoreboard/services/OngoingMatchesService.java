package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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


        Optional<Player> onePlayer = playerDao.findByName(namePlayer1);
        if (!onePlayer.isPresent()) {
            onePlayer = Optional.of(playerDao.save(new Player(0, namePlayer1)));
        }

        Optional<Player> twoPlayer = playerDao.findByName(namePlayer2);
        if (!twoPlayer.isPresent()) {
            twoPlayer = Optional.of(playerDao.save(new Player(0, namePlayer2)));
        }

        UUID uuid = UUID.randomUUID();
        OngoingMatch ongoingMatch = new OngoingMatch(onePlayer.get(), twoPlayer.get());
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

