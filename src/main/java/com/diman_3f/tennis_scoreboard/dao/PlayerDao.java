package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.entity.Player;
import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlayerDao {

    public PlayerDao() {
    }

    public Player findById(int id) {
        try (Session session = UtilSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            Player player = session.get(Player.class, id);
            transaction.commit();
            return player;
        }
    }
    public List<Player> findPlayers() {
        try(Session session = UtilSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            List<Player> players = session.createQuery("from Player", Player.class).getResultList();
            transaction.commit();
            return players;
        }
    }

}
