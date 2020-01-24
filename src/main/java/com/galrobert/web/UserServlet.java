package com.galrobert.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galrobert.domain.User;
import com.galrobert.service.UserService;
import com.galrobert.transfer.CreateUserRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users/get/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramName = "userID";
        String paramValue = req.getParameter(paramName);
        PrintWriter out = resp.getWriter();

        //get path parameter
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        long id = Long.parseLong(pathParts[1]);
        System.out.println(id);

        try {
            User user = userService.getUser(id);
            out.write(user.getId() + " - " + user.getEmail() + ":" + user.getUsername());
        } catch (SQLException e) {
//            resp.sendError(500, "Internal error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
