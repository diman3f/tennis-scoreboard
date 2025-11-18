package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import com.diman_3f.tennis_scoreboard.entities.Match;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class JPAMatchDao implements MatchDao {
    @Override
    public void save(Match entity) {
        Transaction transaction = null;
        try (Session session = UtilSessionFactory.getSession();) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try(Session session = UtilSessionFactory.getSession()) {
            transaction = session.beginTransaction();

            String hql = "from Match " ;

            List<Match> matches = session.createQuery(hql, Match.class).list();
            for (Match match: matches) {
                System.out.println(match);
            }
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
