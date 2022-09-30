package ua.com.javarush.quest.drogunov.quest.controller.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.dto.ui.QuestDto;
import ua.com.javarush.quest.drogunov.quest.service.QuestService;
import ua.com.javarush.quest.drogunov.quest.util.Go;
import ua.com.javarush.quest.drogunov.quest.util.Jsp;
import ua.com.javarush.quest.drogunov.quest.util.Parser;

import java.io.IOException;
import java.util.Optional;

import static ua.com.javarush.quest.drogunov.quest.util.Jsp.Key.QUEST;

@WebServlet(Go.QUEST)
public class QuestServlet extends HttpServlet {
    private final QuestService questService = QuestService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Parser.getId(req);
        Optional<QuestDto> questDto = questService.get(id);
        req.setAttribute(QUEST, questDto);
        Jsp.forward(req, resp, Go.QUEST);
    }
}
