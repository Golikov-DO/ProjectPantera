package com.javarush.khmelov.app.cmd;

import com.javarush.khmelov.app.entity.User;
import com.javarush.khmelov.app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Collection;

public class ListUser implements Command {

    private final UserService userService;

    public ListUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        Collection<User> users = userService.getAll();
        req.setAttribute("users", users);
        return getView();
    }
}
