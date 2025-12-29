package com.javarush.khmelov.app.controller;

import com.javarush.khmelov.app.cmd.Command;
import com.javarush.khmelov.app.config.Winter;
import com.javarush.khmelov.app.entity.Role;
import com.javarush.khmelov.app.entity.User;
import com.javarush.khmelov.app.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"", "/list-user", "/edit-user"}, loadOnStartup = 1)
public class FrontController extends HttpServlet {

    private final HttpResolver httpResolver = Winter.find(HttpResolver.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext appScope = config.getServletContext();
        appScope.setAttribute("roles", Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = httpResolver.resolve(req);
        if (command != null) {
            String view = command.doGet(req);
            String jsp = getJsp(view);
            req.getRequestDispatcher(jsp).forward(req, resp);
        } else {
            super.doGet(req, resp);
        }
    }

    private static String getJsp(String view) {
        return "WEB-INF/" + view + ".jsp";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = httpResolver.resolve(req);
        String redirect = command.doPost(req);
        resp.sendRedirect(redirect);
    }
}
