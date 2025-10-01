package com.diman_3f.tennis_scoreboard.controllers;

import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.services.ApplicationStateInstaller;
import com.diman_3f.tennis_scoreboard.services.MatchCreatorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * создаем новый активный матч и редиректимся на другой сервлет, который обслуживает созданный матч
 */

@WebServlet(name = "MatchCreatorServlet", urlPatterns = "/new-match")
public class MatchCreatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationStateInstaller installer = new ApplicationStateInstaller();
        installer.initializeDefaultDatabase();

        String playerNameOne = req.getParameter("namePlayerOne");
        String playerNameTwo = req.getParameter("namePlayerTwo");


        MatchCreatorService matchCreatorService = new MatchCreatorService(new PlayerDao());
        matchCreatorService.createCurrentMatch(playerNameOne, playerNameTwo);
        String uuid = String.valueOf(matchCreatorService.getUuid());

        String uuidMatch = URLEncoder.encode(uuid, "UTF-8");
        String path = req.getContextPath() + "/match-score?uuid=" + uuidMatch;
        resp.sendRedirect(path);

    }
}

