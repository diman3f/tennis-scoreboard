package com.diman_3f.tennis_scoreboard.controllers;

import com.diman_3f.tennis_scoreboard.context.ServiceLocator;
import com.diman_3f.tennis_scoreboard.dao.JPAMatchDao;
import com.diman_3f.tennis_scoreboard.dto.MatchResultDto;
import com.diman_3f.tennis_scoreboard.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "matches", urlPatterns = "/matches")
public class Matches extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int recordPage = 1;
        JPAMatchDao service = ServiceLocator.getService(JPAMatchDao.class);
        if(req.getParameter("page") != null) {
            int currentPage = Integer.parseInt(req.getParameter("page"));
            int offset = (currentPage - 1) * recordPage;
            List<MatchResultDto> matches = service.getMatchWithOffSet(offset, recordPage);
            int noOfRecords = service.count();
            int noOfPage = (int) Math.ceil(noOfRecords * 1.0 ) / recordPage;
            req.setAttribute("matches", matches);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("noOfPage", noOfPage);
            req.getServletContext().getRequestDispatcher(JspHelper.getPath("matches")).forward(req, resp);
        }
    }


    private List<MatchResultDto> dtoMatches(List<MatchResultDto> dtoMaths, int page) {
        List<MatchResultDto> result = new ArrayList<>();
        int limit = 5;
        int startIndex = 0;
        int offSetIndex = limit * page;
        for (int i = startIndex; i < dtoMaths.size(); i++) {
            result.add(dtoMaths.get(i));
        }
        return result;
    }
}
