package com.diman_3f.tennis_scoreboard.service;

import com.diman_3f.tennis_scoreboard.entity.Player;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.model.ActiveMatch;
import com.diman_3f.tennis_scoreboard.model.ScorePlayer;

import java.util.*;

public class MatchCreatorService {

    private PlayerDao playerDao;
    private Map<UUID, ActiveMatch> matches;
    private UUID uuid;


    public MatchCreatorService(PlayerDao playerDao) {
        this.playerDao = playerDao;
        this.matches = new HashMap<>();
    }

    public UUID createCurrentMatch(String namePlayer1, String namePlayer2) {

        List<Player> players = playerDao.findPlayers();
        Player playerOne = null;
        Player playerTwo = null;
        for (Player player : players) {
            if (player.getName() == namePlayer1) {
                playerOne = player;
                break;
            }
        }
        for (Player player : players) {
            if (player.getName() == namePlayer2) {
                playerTwo = player;
                break;
            }
        }
        //todo проверку ранее созданных игроков сделать в отдельном методе
        if (playerOne != null & playerTwo != null) {


            UUID uuid = UUID.randomUUID();
            this.uuid = uuid;
            ActiveMatch activeMatch = ActiveMatch.builder()
                    .id(uuid)
                    .playerOneID(playerOne.getID())
                    .playerTwoID(playerTwo.getID())
                    .scorePlayerMap(new HashMap<>())
                    .build();
            activeMatch.setScorePlayerOne(new ScorePlayer());
            activeMatch.setScorePlayerTwo(new ScorePlayer());
            matches.put(uuid, activeMatch);
            return uuid;
        }
        throw new NoSuchElementException("Матч не создан");
    }

    public ActiveMatch getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }
}
