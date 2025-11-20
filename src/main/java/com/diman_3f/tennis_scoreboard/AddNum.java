package com.diman_3f.tennis_scoreboard;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddNum extends HttpServlet {
    //1231312312

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get the x, y parameters from the request
        // object and save in num1, num2 variables.
        int num1 = Integer.parseInt(req.getParameter("x"));
        int num2 = Integer.parseInt(req.getParameter("y"));

        // Perform addition operation on num1, num2
        // and save the result in add variable.
        int add = num1 + num2;

        // Set the add value in 'sum'
        // attribute of request object
        req.setAttribute("sum", add);

        // Get the Request Dispatcher object and pass
        // the argument to which servlet we need to call - AvgNum.java
        RequestDispatcher reqd = req.getRequestDispatcher("avg");

        // Forward the Request Dispatcher object.
        reqd.forward(req, resp);
    }

}