package ua.com.javarush.quest.drogunov.quest.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.repository.GameRepository;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.repository.QuestionRepository;
import ua.com.javarush.quest.drogunov.quest.repository.UserRepository;
import ua.com.javarush.quest.drogunov.quest.service.GameService;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;
import ua.com.javarush.quest.drogunov.quest.util.RepositoryFactory;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import static java.util.Objects.isNull;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet extends HttpServlet {
    private final RepositoryFactory repositoryFactory = new RepositoryFactory(DbSession.getSessionFactory());
    private final GameRepository gameRepository = repositoryFactory.getRepository(GameRepository.class);
    private final UserRepository userRepository = repositoryFactory.getRepository(UserRepository.class);
    private final QuestRepository questRepository = repositoryFactory.getRepository(QuestRepository.class);
    private final QuestionRepository questionRepository = repositoryFactory.getRepository(QuestionRepository.class);
    private final GameService gameService = new GameService(gameRepository, userRepository, questRepository, questionRepository);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO test user id 1
        Long questId = Long.parseLong(req.getParameter("questId"));
        HttpSession session = req.getSession();
        Object userId = session.getAttribute("userId");
        GameDTO game;
        if (isNull(userId)) {
            GameDTO gameDTO = createGameDto(questId);
            game = gameService.createGuestGame(gameDTO);
            req.setAttribute("game", game);
        } else {
            //TODO  доделать для зарегистрированного пользователя
        }
        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        super.doPost(req, resp);
    }
    
    private GameDTO createGameDto(Long questId) {
        return GameDTO.builder()
                .user(UserDTO.builder()
                        .id(1L)
                        .build()
                ).quest(
                        QuestDTO.builder()
                                .id(questId)
                                .build()
                )
                .build();
    }
    
}
