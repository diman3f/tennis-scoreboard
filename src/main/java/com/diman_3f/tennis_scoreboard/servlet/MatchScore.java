package com.diman_3f.tennis_scoreboard.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String uuid = req.getParameter("uuid");

        ObjectMapper objectMapper = new ObjectMapper();
        try (PrintWriter writer = resp.getWriter();) {
            writer.println(objectMapper.writeValueAsString("Создан матч с uuid = " + uuid));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String parameter = req.getParameter("id1");
        writer.println("Hello!" + " " + parameter);


    }
}
