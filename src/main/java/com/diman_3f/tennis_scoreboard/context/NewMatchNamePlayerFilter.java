package com.diman_3f.tennis_scoreboard.context;

import com.diman_3f.tennis_scoreboard.utils.JspHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class NewMatchNamePlayerFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String nameOne = req.getParameter("nameOne");
        String nameTwo = req.getParameter("nameTwo");
        if(!checkFormatNamePlayer(nameOne)) {
            req.setAttribute("message", String.format("Укажите имя %s в формате Имя не длинее 15 символов", nameOne));
            req.getServletContext().getRequestDispatcher(JspHelper.getPath("new-match")).forward(req,res);
        }
        if(!checkFormatNamePlayer(nameTwo)) {
            req.setAttribute("message", String.format("Укажите имя %s в формате Имя не длинее 15 символов", nameTwo));
            req.getServletContext().getRequestDispatcher(JspHelper.getPath("new-match")).forward(req,res);
        }
        if(!checkNameOnEquals(nameOne,nameTwo)) {
            req.setAttribute("message", String.format("Укажите разные имена"));
            req.getServletContext().getRequestDispatcher(JspHelper.getPath("new-match")).forward(req,res);
        }
        chain.doFilter(req, res);
    }

    private boolean checkFormatNamePlayer(String name) {
        return (name.matches("/^[А-ЯЁ][а-яё]{1,15}+$/") || name.matches("/^[A-Z][a-z]{1,15}+$/"));
    }
    private boolean checkNameOnEquals(String nameOne, String nameTwo) {
        return nameOne.equals(nameTwo);
    }
}
