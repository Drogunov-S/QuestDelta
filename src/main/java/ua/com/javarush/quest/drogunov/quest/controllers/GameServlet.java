package ua.com.javarush.quest.drogunov.quest.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.drogunov.quest.exceptions.QuestionNotFoundException;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestionDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.GameState;
import ua.com.javarush.quest.drogunov.quest.repository.GameRepository;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.repository.QuestionRepository;
import ua.com.javarush.quest.drogunov.quest.repository.UserRepository;
import ua.com.javarush.quest.drogunov.quest.service.GameService;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;
import ua.com.javarush.quest.drogunov.quest.util.RepositoryFactory;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
        Enumeration<String> attributeNames = req.getAttributeNames();
        Map<String, String[]> parameterMap = req.getParameterMap();
        String gameId = req.getParameter("gameId");
        GameDTO guestGame;
        if (isNull(gameId)) {
            Long questId = Long.parseLong(req.getParameter("questId"));
            guestGame = gameService.createGuestGame(createGameDto(questId));
        } else {
            guestGame = gameService
                    .getCurrentGame(GameDTO.builder()
                            .id(Long.parseLong(gameId))
                            .build()
                    );
        }
        req.setAttribute("game", guestGame);
        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        long userAnswerId = Long.parseLong(req.getParameter("checkAnswer"));
        long gameId = Long.parseLong(req.getParameter("btnSave"));
        if (gameService.isTrueAnswer(userAnswerId, gameId)) {
            gameService.
                    updateGameData(GameDTO.builder()
                            .id(gameId)
                            .build());
            try {
                resp.sendRedirect("/game?gameId=" + gameId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
                .gameState(GameState.PLAY)
                .build();
    }
    
}
