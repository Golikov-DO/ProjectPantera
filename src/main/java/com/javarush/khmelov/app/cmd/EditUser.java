package com.javarush.khmelov.app.cmd;

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

public class EditUser implements Command {

    private final UserService userService;

    public EditUser(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String doGet(HttpServletRequest req) {
        String strId = req.getParameter("id");
        if (strId != null && !strId.isEmpty()) {
            long id = Long.parseLong(strId);
            User user = userService.get(id).orElseThrow();
            req.setAttribute("user", user);
        }
        return getView();
    }

    @Override
    public String doPost(HttpServletRequest req)  {
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
            throw new RuntimeException("incorrect form data");
        }
        return getView()+"?id=" + user.getId();
    }
}
