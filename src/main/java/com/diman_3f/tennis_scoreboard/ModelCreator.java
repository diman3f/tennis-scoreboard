package com.diman_3f.tennis_scoreboard;

import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import com.diman_3f.tennis_scoreboard.dao.MatchDao;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.entity.Match;
import com.diman_3f.tennis_scoreboard.entity.Player;
import com.diman_3f.tennis_scoreboard.model.ActiveMatch;
import com.diman_3f.tennis_scoreboard.service.MatchCreatorService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.UUID;

public class ModelCreator {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml"); // подгружаем конфигурацию в класс

        try (Session session = UtilSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            Player player1 = Player.builder()
                    .name("Ivan")
                    .build();
            Player player2 = Player.builder()
                    .name("Oleg")
                    .build();

            Player player3 = Player.builder()
                    .name("Roman")
                    .build();
//            Match match = Match.builder()
//                    .player1(player1)
//                    .player2(player3)
//                    .winner(player1)
//                    .build();

            session.persist(player1);
            session.persist(player2);
            session.persist(player3);
//            session.persist(match);

            System.out.println(player1.getID() + " " + player1.getName());
            System.out.println(player2.getID() + " " + player2.getName());
//            System.out.println(match.getID() + " " + match.getPlayer1().getName() + " " +
//                    match.getPlayer2().getName() + " " + match.getWinner().getName());
            transaction.commit();
        }

        PlayerDao playerDao = new PlayerDao();
        MatchCreatorService matchCreatorService = new MatchCreatorService(playerDao);

        List<Player> players = new PlayerDao().findPlayers();
        for (Player player : players) {
            System.out.println(player.toString());

            matchCreatorService.createCurrentMatch("Ivan", "Oleg");
            UUID uuid = matchCreatorService.getUuid();
            ActiveMatch match = matchCreatorService.getMatch(uuid);


        }
        }
    }


