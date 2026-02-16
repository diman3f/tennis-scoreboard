package com.diman_3f.tennis_scoreboard.controllers;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.dao.JPAMatchDao;
import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.models.OngoingMatch;
import com.diman_3f.tennis_scoreboard.services.*;
import com.diman_3f.tennis_scoreboard.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScore extends HttpServlet {
    private MatchScoreController controller;

    @Override
    public void init() throws ServletException {
        OngoingMatchesService matchesService = ServiceLocator.getService(OngoingMatchesService.class);
        MatchScoreCalculationService calculationService = new MatchScoreCalculationService(ServiceLocator.getService(TennisRuleHandler.class));
        FinishedMatchesPersistenceService finishedService = new FinishedMatchesPersistenceService(ServiceLocator.getService(JPAMatchDao.class));
        this.controller = new MatchScoreController(matchesService, calculationService, finishedService);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        req.setAttribute("uuid", uuid);
        OngoingMatchesService service = ServiceLocator.getService(OngoingMatchesService.class);
        OngoingMatch match = service.getMatch(uuid);
        ScoreDto dto = match.getScore();
        req.setAttribute("dto", dto);
        getServletContext().getRequestDispatcher(JspHelper.getPath("matchScore"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page;
        String playerWon = req.getParameter("playerWon");
        int numberWonPlayer = 0;
        if (playerWon.equals("twoPlayerWinPoint")) {
            numberWonPlayer = 1;
        }
        String uuid = req.getParameter("uuid");
        req.setAttribute("uuid", uuid);
        ScoreDto score = controller.addPoint(numberWonPlayer, uuid);
        req.setAttribute("dto", score);
        if (score.isFinished()) {
            page = "index";
        } else {
            page = "matchScore";
        }
        getServletContext().getRequestDispatcher(JspHelper.getPath(page)).
                forward(req, resp);
    }
}