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

@WebServlet("/admin-user-edit")
public class AdminUserEditController extends HttpServlet {

    private AdminService adminService;

    @Override
    public void init() {
        adminService = (AdminService) getServletContext().getAttribute("adminService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        // ===== ПРОВЕРКА АДМИНА =====
        User current = (User) req.getSession().getAttribute("user");
        if (current == null || current.role() != Role.ADMIN) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        // ===== ДАННЫЕ =====
        User editUser = adminService.findUser(req.getParameter("login"));

        req.setAttribute("editUser", editUser);
        req.setAttribute("users", adminService.getAllUsers());

        // ===== VIEW =====
        req.setAttribute("view", "/WEB-INF/jsp/admin-users.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
