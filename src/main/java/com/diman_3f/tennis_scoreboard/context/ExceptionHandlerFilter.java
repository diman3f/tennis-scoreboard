package com.diman_3f.tennis_scoreboard.context;

import com.diman_3f.tennis_scoreboard.exception.InternalServerError;
import com.diman_3f.tennis_scoreboard.exception.ValidationException;
import com.diman_3f.tennis_scoreboard.utils.JspHelper;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class ExceptionHandlerFilter extends HttpFilter {
    private final int NOT_FOUND = 404;
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req,res);
        } catch (RuntimeException e) {
            if (e instanceof EntityExistsException) {
                req.setAttribute("error", "403");
                req.setAttribute("message", e.getMessage());
                forwardToErrorPage("new-match", req, res);
            } else if (e instanceof ValidationException) {
                req.setAttribute("error", "401");
                req.setAttribute("message", e.getMessage());
                forwardToErrorPage("new-match", req, res);
            } else if (e instanceof InternalServerError) {
                req.setAttribute("error", "500");
                req.setAttribute("message", e.getMessage());
                forwardToErrorPage("error", req, res);
            } else {
            }
        }
    }
    private void forwardToErrorPage(String page,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(JspHelper.getPath(page)).forward(req,res);
    }


}
