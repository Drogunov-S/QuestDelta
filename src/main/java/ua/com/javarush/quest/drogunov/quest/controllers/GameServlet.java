package ua.com.javarush.quest.drogunov.quest.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.GameState;
import ua.com.javarush.quest.drogunov.quest.service.GameService;

import java.io.IOException;

import static java.util.Objects.isNull;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet extends HttpServlet {
    private final GameService gameService = new GameService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        } else {
            req.setAttribute("error", "Неверный ответ, попробуй еще раз");
            try {
                req.getRequestDispatcher("/quests").forward(req, resp);
//                req.getRequestDispatcher("/game?gameId=" + gameId).forward(req, resp);
            } catch (IOException | ServletException e) {
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
