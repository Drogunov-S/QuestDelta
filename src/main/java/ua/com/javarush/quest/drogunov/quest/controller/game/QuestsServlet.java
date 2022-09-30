package ua.com.javarush.quest.drogunov.quest.controller.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.service.QuestService;
import ua.com.javarush.quest.drogunov.quest.util.Go;
import ua.com.javarush.quest.drogunov.quest.util.Jsp;

import java.io.IOException;

import static ua.com.javarush.quest.drogunov.quest.util.Jsp.Key.QUESTS;

@WebServlet({Go.HOME, Go.QUESTS})
public class QuestsServlet extends HttpServlet {

    private final QuestService questService = QuestService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(QUESTS, questService.getAll());
        Jsp.forward(req, resp, Go.QUESTS);
    }
}
