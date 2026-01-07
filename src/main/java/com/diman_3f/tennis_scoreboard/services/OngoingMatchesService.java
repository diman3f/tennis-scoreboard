package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.context.Validator;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.dto.NewMatchDto;
import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

import java.net.URLEncoder;
import java.util.*;

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


    public NewMatchDto createCurrentMatch(NewMatchDto dto) {
        String namePlayerOne = dto.getNameOnePlayer();
        String namePlayerTwo = dto.getNameTwoPlayer();



        if (!(Validator.isValidName(namePlayerOne))) {
            dto.saveErrors("nameOne", String.format("Имя пользователя %s не соответствует формату Имя Фамилия", namePlayerOne));
        }
        if (!(Validator.isValidName(namePlayerTwo))) {
            dto.saveErrors("nameTwo", String.format("Имя пользователя %s не соответствует формату Имя Фамилия", namePlayerTwo));
        }
        if (!Validator.isValidLength(namePlayerOne)) {
            dto.saveErrors("lengthOne", "Не допускается длина более 50 символов");
        }
        if (!Validator.isValidLength(namePlayerTwo)) {
            dto.saveErrors("lengthTwo", "Не допускается длина более 50 символов");
        }


        Optional<Player> onePlayer = playerDao.findByName(namePlayerOne);
        if (!onePlayer.isPresent()) {
            onePlayer = Optional.of(playerDao.save(new Player(0, namePlayerOne)));
        }

        Optional<Player> twoPlayer = playerDao.findByName(namePlayerTwo);
        if (!twoPlayer.isPresent()) {
            twoPlayer = Optional.of(playerDao.save(new Player(0, namePlayerTwo)));
        }

        UUID uuid = UUID.randomUUID();
        Player playerOne = onePlayer.get();
        Player playerTwo = twoPlayer.get();
        OngoingMatch ongoingMatch = new OngoingMatch(playerOne, playerTwo);
        matches.put(uuid, ongoingMatch);
        dto.setUuid(uuid);
        return dto;
    }

    public OngoingMatch getMatch(String uuid) {
        return matches.get(UUID.fromString(uuid));
    }

    public void deleteMatchByUUID(UUID uuid) {
        matches.remove(uuid);
    }

}

