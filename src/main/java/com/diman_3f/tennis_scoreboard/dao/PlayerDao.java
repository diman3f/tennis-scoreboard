package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.NoSuchElementException;

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
        try (Session session = UtilSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            List<Player> players = session.createQuery("from Player", Player.class).getResultList();
            transaction.commit();
            return players;
        }
    }

    public Player findByName(String namePlayer) {
        try (Session session = UtilSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "from Player player where player.name=:name_player";
            Query query = session.createQuery(hql, Player.class);
            query.setParameter("name_player", namePlayer);
            List<Player> list = query.getResultList();
            transaction.commit();
            for (Player player : list) {
                if (player.getName().equals(namePlayer)) {
                    return player;
                }
            }
        }
        throw new NoSuchElementException(String.format("Игрок с именем %s", namePlayer + " не найден"));
    }

}
