package com.javarush.khmelov.lesson13.controller;

import com.javarush.khmelov.lesson13.config.Winter;
import com.javarush.khmelov.lesson13.entity.Role;
import com.javarush.khmelov.lesson13.entity.User;
import com.javarush.khmelov.lesson13.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/edit-user", loadOnStartup = 1)
public class EditUser extends HttpServlet {

    private final UserService userService = Winter.find(UserService.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext appScope = config.getServletContext();
        appScope.setAttribute("roles", Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        if (strId != null && !strId.isEmpty()) {
            long id = Long.parseLong(strId);
            User user = userService.get(id).orElseThrow();
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("WEB-INF/edit-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        Long id = strId == null || strId.isEmpty()
                ? null
                : Long.parseLong(strId);
        User user = User.builder()
                .id(id)
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .role(Role.valueOf(req.getParameter("role")))
                .build();
        if (req.getParameter("create") != null) {
            userService.create(user);
        } else if (req.getParameter("update") != null) {
            userService.update(user);
        } else {
            throw new ServletException("incorrect form data");
        }
        resp.sendRedirect("edit-user?id=" + user.getId());
    }
}
