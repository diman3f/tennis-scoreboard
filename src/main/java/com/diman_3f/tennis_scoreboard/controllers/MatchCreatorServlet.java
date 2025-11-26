package com.diman_3f.tennis_scoreboard.controllers;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.dao.PlayerDao;
import com.diman_3f.tennis_scoreboard.services.ApplicationStateInstaller;
import com.diman_3f.tennis_scoreboard.services.MatchCreatorService;
import com.diman_3f.tennis_scoreboard.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * создаем новый активный матч и редиректимся на другой сервлет, который обслуживает созданный матч
 */

@WebServlet(name = "MatchCreatorServlet", urlPatterns = "/new-match")
public class MatchCreatorServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(JspHelper.getPath("new-match"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ApplicationStateInstaller installer = new ApplicationStateInstaller();
        installer.initializeDefaultDatabase();


        String playerNameOne = req.getParameter("nameOne");
        String playerNameTwo = req.getParameter("nameTwo");

        UUID uuidMatch = ServiceLocator.getService(MatchCreatorService.class)
                .createCurrentMatch(playerNameOne, playerNameTwo);
        String uuid = URLEncoder.encode(String.valueOf(uuidMatch), "UTF-8");

        String path = req.getContextPath() + "/match-score?uuid=" + uuid;
        resp.sendRedirect(path);

    }
}

