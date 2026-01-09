package com.diman_3f.tennis_scoreboard.controllers;


import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.dao.JPAMatchDao;
import com.diman_3f.tennis_scoreboard.dao.MatchDao;
import com.diman_3f.tennis_scoreboard.entities.Match;
import com.diman_3f.tennis_scoreboard.utils.JspHelper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "saveMatch", urlPatterns = "/save")
public class SaveMatch extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String one = req.getParameter("playerIdOne");
        String two = req.getParameter("playerIdTwo");
        String winner = req.getParameter("winner");
//        Match match = Match.builder()
//                .id(0)
//                .player1(Integer.parseInt(one))
//                .player2(Integer.parseInt(two))
//                .winner(Integer.parseInt(winner))
//                .build();
//        JPAMatchDao dao = ServiceLocator.getService(JPAMatchDao.class);
//        dao.save(match);
//        String parameter = req.getParameter("findId");
//        Match matchById = dao.findById(Integer.parseInt(parameter));
//
//        req.setAttribute("id", matchById.getId());
//        req.setAttribute("playerOne", matchById.getPlayer1());
//        req.setAttribute("playerTwo", matchById.getPlayer2());
//        req.setAttribute("winner", matchById.getWinner());
//        req.getRequestDispatcher(JspHelper.getPath("matches"))
//                .forward(req, resp);

    }
}
