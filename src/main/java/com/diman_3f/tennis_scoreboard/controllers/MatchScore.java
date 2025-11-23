package com.diman_3f.tennis_scoreboard.controllers;

import com.diman_3f.tennis_scoreboard.dto.ScoreDto;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("uuid", req.getParameter("uuid"));
        ScoreDto dto = new ScoreDto(1, 2, "0", 0, 0, "0", 0, 0);
        req.setAttribute("dto", dto);
        getServletContext().getRequestDispatcher(JspHelper.getPath("match-score_jsp"))
                .forward(req,resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        ScoreDto dto = new ScoreDto(1, 2, "15", 4, 1, "30", 2, 0);
        req.setAttribute("dto", dto);
        req.setAttribute("uuid", uuid);
        req.setAttribute("id", req.getParameter("playerId"));
        getServletContext().getRequestDispatcher(JspHelper.getPath("match-score_jsp")).
                forward(req,resp);
    }
}


