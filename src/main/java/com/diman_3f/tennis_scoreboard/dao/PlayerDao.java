package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.ranges.RangeException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Player save(Player player) {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
            return player;
        } catch (RuntimeException e) {
            throw new RuntimeException("Объект уже существует");
        }
    }


    public Optional<Player> findByName(String namePlayer) {
        try (Session session = UtilSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "from Player player where player.name=:name_player";
            Query query = session.createQuery(hql, Player.class);
            Player name_player = (Player) query.setParameter("name_player", namePlayer).getSingleResult();
            transaction.commit();
            return Optional.of(name_player);
        } catch (PersistenceException e) {
            return Optional.empty();
        }
    }
}

