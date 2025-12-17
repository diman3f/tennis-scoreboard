package com.diman_3f.tennis_scoreboard.services;

import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.entities.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Класс для заполнения бд исходными данными
 */

public class ApplicationStateInstaller {

    public void initializeDefaultDatabase(){
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

            Match match1 = Match.builder()
                    .player1(player1)
                    .player2(player2)
                    .winner(player1)
                    .build();

            session.persist(player1);
            session.persist(player2);
            session.persist(player3);
            session.persist(match1);
            transaction.commit();
        }
    }

}
