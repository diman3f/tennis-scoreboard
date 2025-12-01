package com.diman_3f.tennis_scoreboard.controllers;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.dao.JPAMatchDao;
import com.diman_3f.tennis_scoreboard.dao.MatchDao;
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
        FinishedMatchesPersistenceService finishedService= new FinishedMatchesPersistenceService(ServiceLocator.getService(JPAMatchDao.class));
        this.controller = new MatchScoreController(matchesService, calculationService,finishedService);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        req.setAttribute("uuid", uuid);
        OngoingMatchesService service = ServiceLocator.getService(OngoingMatchesService.class);
        OngoingMatch match = service.getMatch(uuid);

        req.setAttribute("playerOneId", match.getPlayerOneId());
        req.setAttribute("playerTwoId", match.getPlayerTwoId());

        ScoreDtoFormatter dtoFormatter = new ScoreDtoFormatter();
        req.setAttribute("dto", dtoFormatter.createDto(match));

        getServletContext().getRequestDispatcher(JspHelper.getPath("match-score_jsp"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String playerId = req.getParameter("playerId");
        String uuid = req.getParameter("uuid");
        req.setAttribute("uuid", uuid);

        //в оконцовке получаем готовый дто модели
        // контроллер принимает id и начисляет очко и выводит dto

        ScoreDto dto = controller.addPoint(playerId, uuid);

        //сервис анализирует состояние счета матча
        //если счет не завершен передаем в работу другому сервису
        // если завершен обрабатываем и переходим на страницу финального счета

        req.setAttribute("dto", dto);
        getServletContext().getRequestDispatcher(JspHelper.getPath("match-score_jsp")).
                forward(req, resp);
    }
}


