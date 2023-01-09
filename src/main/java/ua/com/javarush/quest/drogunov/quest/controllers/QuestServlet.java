package ua.com.javarush.quest.drogunov.quest.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.service.QuestService;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;
import ua.com.javarush.quest.drogunov.quest.util.RepositoryFactory;

import java.io.IOException;

@WebServlet(name = "QuestServlet", value = "/quest")
public class QuestServlet extends HttpServlet {
    private final RepositoryFactory repositoryFactory = new RepositoryFactory(DbSession.getSessionFactory());
    private final QuestRepository questRepository = repositoryFactory.getRepository(QuestRepository.class);
    private final QuestService questService = new QuestService(questRepository);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questId = Long.parseLong(req.getParameter("questId"));
        QuestDTO quest = questService.getQuestById(questId);
        req.setAttribute("quest", quest);
        req.getRequestDispatcher("/quest.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
