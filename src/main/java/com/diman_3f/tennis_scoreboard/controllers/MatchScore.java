package com.diman_3f.tennis_scoreboard.controllers;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
import com.diman_3f.tennis_scoreboard.models.ActiveMatch;
import com.diman_3f.tennis_scoreboard.services.MatchCreatorService;
import com.diman_3f.tennis_scoreboard.services.MatchScoreCalculationService;
import com.diman_3f.tennis_scoreboard.services.ScoreDtoFormatter;
import com.diman_3f.tennis_scoreboard.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/match-score")
public class MatchScore extends HttpServlet {

    private MatchCreatorService service;

    @Override
    public void init() throws ServletException {
        this.service = new MatchCreatorService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        req.setAttribute("uuid", uuid);
        MatchCreatorService service = ServiceLocator.getService(MatchCreatorService.class);
        ActiveMatch match = service.getMatch(uuid);

        req.setAttribute("playerOneId", match.getPlayerOneId());
        req.setAttribute("playerTwoId", match.getPlayerTwoId());

        ScoreDtoFormatter dtoFormatter = new ScoreDtoFormatter(match);
        req.setAttribute("dto", dtoFormatter.createDto());

        getServletContext().getRequestDispatcher(JspHelper.getPath("match-score_jsp"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String playerId = req.getParameter("playerId");

        MatchCreatorService service = ServiceLocator.getService(MatchCreatorService.class);
        String uuid = req.getParameter("uuid");
        req.setAttribute("uuid", uuid);

        ActiveMatch match = service.getMatch(uuid);
        MatchScoreCalculationService serviceScore = new MatchScoreCalculationService(match);
        req.setAttribute("dto", serviceScore.upPoint(Integer.valueOf(playerId)));
        getServletContext().getRequestDispatcher(JspHelper.getPath("match-score_jsp")).
                forward(req, resp);
    }
}


