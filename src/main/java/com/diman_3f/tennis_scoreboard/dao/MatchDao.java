package com.diman_3f.tennis_scoreboard.dao;

import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.context.UtilSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDao {

    public List<Match> findAllMatch() {
        try (Session session = UtilSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            List<Match> match = session.createQuery("from Match", Match.class).getResultList();
            transaction.commit();
            return match;
        }
    }
}
