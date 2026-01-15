package com.javarush.golikov.quest.web;

import com.javarush.golikov.quest.auth.Role;
import com.javarush.golikov.quest.model.User;
import com.javarush.golikov.quest.service.AdminService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin-user-save")
public class AdminUserSaveController extends HttpServlet {
    private AdminService adminService;

    @Override
    public void init() {
        adminService = (AdminService) getServletContext().getAttribute("adminService");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));

        adminService.saveUser(new User(login, pass, role));
        resp.sendRedirect(req.getContextPath() + "/admin-users");
    }
}

