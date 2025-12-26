package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import com.diman_3f.tennis_scoreboard.dto.MatchResultDto;
import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.entities.MatchModelMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class JPAMatchDao implements MatchDao {



    @Override
    public void save(Match entity) {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession();) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try (Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();

            String hql = "from Match ";

            List<Match> matches = session.createQuery(hql, Match.class).list();
            for (Match match : matches) {
                System.out.println(match);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
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
            transaction.rollback();
            throw new RuntimeException("ошибка получения колчество записей");
        }
    }

    @Override
    public List<MatchResultDto> getMatchWithOffSet(int offset, int limit) {
        Transaction transaction = null;
        try(Session session = UtilSessionFactory.getSession()) {
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
            for(Match match : resultList) {
                matches.add(new MatchResultDto(match.getPlayer1().getName(), match.getPlayer2().getName(), match.getWinner().getName()));
            }
            transaction.commit();
            return matches;
        } catch (RuntimeException e) {
            transaction.rollback();;
            throw new RuntimeException("Ошибка в получении выборки матчей");
        }
    }

    @Override
    public Match findById(int id) {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();


            String hql = "FROM Match match WHERE match.id = :id";

            Query<Match> query = session.createQuery(hql, Match.class);
            query.setParameter("id", id);
            Match match = query.getSingleResult();
            transaction.commit();
            return match;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new NoSuchElementException("ошибка в получении игрока");
        }
    }

    @Override
    public MatchResultDto findByName(String name) {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();
            String hql = "select m from Match m " +
                    "join fetch m.player1 p1 " +
                    "join fetch m.player2 p2 " +
                    "join fetch m.winner " +
                    "where p1.name =:namePlayer or p2.name =:namePlayer";

            Query<Match> query = session.createQuery(hql, Match.class);
            Query<Match> result = query.setParameter("namePlayer", name);
            Match singleResult = result.getSingleResult();
            MatchResultDto matchPlayerName = new MatchResultDto(singleResult.getPlayer1().getName(), singleResult.getPlayer2().getName(), singleResult.getWinner().getName());
            transaction.commit();
            return matchPlayerName;
        } catch (RuntimeException e) {
            throw new NoSuchElementException("");
        }
    }

    @Override
    public List<MatchResultDto> findAll() {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();
            String hql = "select m from Match m " +
                    "join fetch m.player1 p1 " +
                    "join fetch m.player2 p2 " +
                    "join fetch m.winner pw";


            Query<Match> query = session.createQuery(hql, Match.class);
            List<Match> resultList = query.getResultList();
            transaction.commit();
            return toMatch(resultList);
        } catch (RuntimeException e) {
            throw new NoSuchElementException("");
        }
    }

    private List<MatchResultDto> toMatch(List<Match> matches) {
        List<MatchResultDto> dto = new ArrayList<>();
        for (Match match : matches) {
            dto.add(new MatchResultDto(match.getPlayer1().getName(), match.getPlayer2().getName(), match.getWinner().getName()));
        }
        return dto;
    }
}
