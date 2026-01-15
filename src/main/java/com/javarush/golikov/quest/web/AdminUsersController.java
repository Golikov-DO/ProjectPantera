package com.javarush.golikov.quest.web;

import com.javarush.golikov.quest.auth.Role;
import com.javarush.golikov.quest.model.User;
import com.javarush.golikov.quest.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin-users")
public class AdminUsersController extends HttpServlet {
    private AdminService adminService;

    @Override
    public void init() {
        adminService = (AdminService) getServletContext().getAttribute("adminService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.role() != Role.ADMIN) {
            resp.sendRedirect("index.jsp");
            return;
        }

        req.setAttribute("users", adminService.getAllUsers());
        req.setAttribute("view", "/WEB-INF/jsp/admin-users.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

