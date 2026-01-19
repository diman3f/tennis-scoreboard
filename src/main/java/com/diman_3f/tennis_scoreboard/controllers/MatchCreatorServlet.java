package com.diman_3f.tennis_scoreboard.controllers;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;

import com.diman_3f.tennis_scoreboard.dto.NewMatchDto;
import com.diman_3f.tennis_scoreboard.utils.JspHelper;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "MatchCreatorServlet", urlPatterns = "/new-match")
public class MatchCreatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(JspHelper.getPath("new-match"))
                .forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerNameOne = req.getParameter("nameOne");
        String playerNameTwo = req.getParameter("nameTwo");
        NewMatchDto dto = new NewMatchDto(playerNameOne, playerNameTwo);
        NewMatchDto match = ServiceLocator.getService(com.diman_3f.tennis_scoreboard.services.OngoingMatchesService.class)
                .createCurrentMatch(dto);
        if (match.isValidDto()) {
            String uuid = URLEncoder.encode(String.valueOf(dto.getUuid()), "UTF-8");
            String path = req.getContextPath() + "/match-score?uuid=" + uuid;
            resp.sendRedirect(path);
        } else {
            req.setAttribute("noValidDto", dto);
            req.getServletContext().getRequestDispatcher(JspHelper.getPath("/new-match")).
                    forward(req, resp);
        }
    }
}

