package com.javarush.golikov.quest.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

import com.javarush.golikov.quest.model.User;
import com.javarush.golikov.quest.auth.Role;

@WebServlet("/admin")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.role() != Role.ADMIN) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        req.setAttribute("view", "/WEB-INF/jsp/admin.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

