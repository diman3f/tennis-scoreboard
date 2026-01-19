package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import com.diman_3f.tennis_scoreboard.dto.MatchResultDto;
import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.exception.DatabaseOperationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class JPAMatchDao implements CrudDao<Match> {

    @Override
    public void save(Match entity) {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (ConstraintViolationException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseOperationException("Failed to save match to the database");
        }
    }

    @Override
    public int count() {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession()) {
             transaction = session.beginTransaction();
            List<Match> matches = session.createQuery("from Match ", Match.class).list();
            transaction.commit();
            return matches.size();
        } catch (RuntimeException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseOperationException("Error fetching matches data");
        }
    }

    @Override
    public List<MatchResultDto> getMatchWithOffSet(int offset, int limit) {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();
            String hql = "from Match as m " +
                    "left join fetch  m.player1 as onePlayer " +
                    "left join fetch  m.player2 as twoPlayer " +
                    "left join fetch m.winner as winner ";
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            List<Match> resultList = query.getResultList();
            List<MatchResultDto> matches = new ArrayList<>();
            for (Match match : resultList) {
                matches.add(new MatchResultDto(match.getPlayer1().getName(), match.getPlayer2().getName(), match.getWinner().getName()));
            }
            transaction.commit();
            return matches;
        } catch (RuntimeException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseOperationException("Error fetching matches data");
        }
    }

    @Override
    public List<MatchResultDto> findByName(String name) {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();
            String hql = "select m from Match m " +
                    "join fetch m.player1 p1 " +
                    "join fetch m.player2 p2 " +
                    "join fetch m.winner " +
                    "where p1.name =:name or p2.name =:name";
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setParameter("name", name);
            List<Match> resultList = query.getResultList();
            List<MatchResultDto> matches = new ArrayList<>();
            for (Match match : resultList) {
                matches.add(new MatchResultDto(match.getPlayer1().getName(), match.getPlayer2().getName(), match.getWinner().getName()));
            }
            transaction.commit();
            return matches;
        } catch (RuntimeException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseOperationException("Match not found");
        }
    }

}
