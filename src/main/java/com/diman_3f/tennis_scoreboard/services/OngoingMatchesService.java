package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.exception.ValidationException;
import com.diman_3f.tennis_scoreboard.utils.Validator;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.dto.NewMatchDto;
import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;

import java.util.*;

public class OngoingMatchesService {

    private final PlayerDao playerDao;
    private final Map<UUID, OngoingMatch> matches;

    public OngoingMatchesService() {
        this.playerDao = ServiceLocator.getService(PlayerDao.class);
        this.matches = new HashMap<>();
    }

    public NewMatchDto createCurrentMatch(NewMatchDto dto) {
        String namePlayerOne = dto.getNameOnePlayer();
        String namePlayerTwo = dto.getNameTwoPlayer();

        if (!(Validator.isValidName(namePlayerOne))) {
            throw new ValidationException(String.format("Ошибка ввода: %s. Имя должно состоять только из букв и начинаться с заглавной буквы", namePlayerOne));
        }
        if (!(Validator.isValidName(namePlayerTwo))) {
            throw new ValidationException(String.format("Ошибка ввода: %s. Имя должно состоять только из букв и начинаться с заглавной буквы", namePlayerTwo));
        }
        if (!Validator.isValidLength(namePlayerOne)) {
            dto.saveErrors("lengthOne", "Длина имени не должно привышать 15 символов");
        }
        if (!Validator.isValidLength(namePlayerTwo)) {
            dto.saveErrors("lengthTwo", "Длина имени не должно привышать 15 символов");
        }

        Optional<Player> onePlayer = playerDao.findByName(namePlayerOne);

        if (!onePlayer.isPresent()) {
            onePlayer = Optional.of(playerDao.save(new Player(namePlayerOne)));
        }
        Optional<Player> twoPlayer = playerDao.findByName(namePlayerTwo);
        if(!twoPlayer.isPresent()) {
            twoPlayer = Optional.of(playerDao.save(new Player(namePlayerTwo)));
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


}

