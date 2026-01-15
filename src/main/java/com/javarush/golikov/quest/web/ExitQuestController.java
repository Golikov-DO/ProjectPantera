package com.javarush.golikov.quest.web;

import com.javarush.golikov.quest.model.QuestSession;
import com.javarush.golikov.quest.service.QuestService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/exitQuest")
public class ExitQuestController extends HttpServlet {
    private QuestService questService;

    @Override
    public void init() {
        questService = (QuestService) getServletContext().getAttribute("questService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        QuestSession qs = (QuestSession) req.getSession().getAttribute("quest");

        if (qs != null) {
            questService.exitQuest(qs);
            req.setAttribute("result", "lose");
            req.getSession().removeAttribute("quest");
        }

        // показываем экран результата
        req.setAttribute("view", "/WEB-INF/jsp/result.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

