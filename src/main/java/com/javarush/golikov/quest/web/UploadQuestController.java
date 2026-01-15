package com.javarush.golikov.quest.web;

import com.javarush.golikov.quest.auth.Role;
import com.javarush.golikov.quest.model.User;
import com.javarush.golikov.quest.service.AdminService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.logging.Logger;

@WebServlet("/upload")
@MultipartConfig
public class UploadQuestController extends HttpServlet {

    private static final Logger log =
            Logger.getLogger(UploadQuestController.class.getName());

    private AdminService adminService;

    @Override
    public void init() {
        adminService = (AdminService) getServletContext().getAttribute("adminService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        // ===== ПРОВЕРКА АДМИНА =====
        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.role() != Role.ADMIN) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        Part file = req.getPart("file");
        String id = req.getParameter("id");
        String title = req.getParameter("title");

        try (InputStream is = file.getInputStream()) {

            adminService.loadQuestFromTxt(id, title, is);

            // redirect НА КОНТРОЛЛЕР АДМИН-КВЕСТОВ
            resp.sendRedirect(req.getContextPath() + "/admin-quests");

        } catch (Exception e) {

            log.severe("Ошибка загрузки квеста '" + id + "': " + e.getMessage());

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Load error");
        }
    }
}
