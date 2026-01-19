package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import com.diman_3f.tennis_scoreboard.entities.Player;
import com.diman_3f.tennis_scoreboard.exception.DatabaseException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Optional;

public class PlayerDao {

    public Player save(Player player) {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
            return player;
        } catch (PersistenceException e) {
            if (e instanceof ConstraintViolationException) {
                if(transaction != null) {
                    transaction.rollback();
                }
                throw new EntityExistsException("Names players must be different");
            }
                throw new DatabaseException("Database not available");
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

