package com.diman_3f.tennis_scoreboard.service;

import com.diman_3f.tennis_scoreboard.entity.Player;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.model.ActiveMatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MatchCreatorService {

    private PlayerDao playerDao;
    private Map<UUID, ActiveMatch> matches;
    private UUID uuid;


    public MatchCreatorService(PlayerDao playerDao) {
        this.playerDao = playerDao;
        this.matches = new HashMap<>();
    }

    public void createCurrentMatch(String namePlayer1, String namePlayer2) {

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

            // это пока не нужно так как по ТЗ создаем сначало матч без записи в БД, запись в бд матча занимается
            // другой сервис
//            try (Session session = UtilSessionFactory.getSession()) {
//                Transaction transaction = session.beginTransaction();
//
//                Match match = Match.builder()
//                        .player1(playerOne)
//                        .player2(playerTwo)
//                        .build();
//                session.persist(match);
//                transaction.commit();
            UUID uuid = UUID.randomUUID();
            this.uuid = uuid;
            ActiveMatch activeMatch = ActiveMatch.builder()
                    .playerOneID(playerOne.getID())
                    .playerTwoID(playerTwo.getID())
                    .score(new Score())
                    .build();
            matches.put(uuid, activeMatch);
            }
        }

        public ActiveMatch getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }
}
