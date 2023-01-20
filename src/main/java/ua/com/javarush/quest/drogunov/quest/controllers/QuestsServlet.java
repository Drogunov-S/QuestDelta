package ua.com.javarush.quest.drogunov.quest.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.service.QuestService;

import java.io.IOException;
import java.util.List;

@WebServlet(displayName = "QuestsServlet", value = {"", "/", "/quests"})
public class QuestsServlet extends HttpServlet {
    private final QuestService questService = new QuestService();
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<QuestDTO> quests = questService.getQuests();
        req.setAttribute("quests", quests);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questId = req.getParameter("quest_id");
        System.out.println(questId);
        super.doPost(req, resp);
    }
}
