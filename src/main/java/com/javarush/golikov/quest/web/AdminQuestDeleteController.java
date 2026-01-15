package com.javarush.golikov.quest.web;

import com.javarush.golikov.quest.service.AdminService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin-quest-delete")
public class AdminQuestDeleteController extends HttpServlet {
    private AdminService adminService;

    @Override
    public void init() {
        adminService = (AdminService) getServletContext().getAttribute("adminService");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        adminService.deleteQuest(req.getParameter("id"));
        resp.sendRedirect(req.getContextPath() + "/admin-quests");
    }
}

