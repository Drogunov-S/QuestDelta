package ua.com.javarush.quest.drogunov.quest.controller.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.dto.FormData;
import ua.com.javarush.quest.drogunov.quest.dto.ui.GameDto;
import ua.com.javarush.quest.drogunov.quest.dto.ui.UserDto;
import ua.com.javarush.quest.drogunov.quest.service.GameService;
import ua.com.javarush.quest.drogunov.quest.util.Go;
import ua.com.javarush.quest.drogunov.quest.util.Jsp;
import ua.com.javarush.quest.drogunov.quest.util.Parser;

import java.io.IOException;
import java.util.Optional;

@WebServlet(Go.GAME)
public class GameServlet extends HttpServlet {

    private static final GameService gameService = GameService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<UserDto> opUserDto = Parser.getUser(req.getSession());
        FormData formData = FormData.of(req);
        if (opUserDto.isPresent()) {
            Optional<GameDto> opGameDto = gameService.getGame(formData, opUserDto.get().getId());
            if (opGameDto.isPresent()) {
                GameDto gameDto = opGameDto.get();
                sendNextStep(req, resp, gameDto);
                return;
            }
            Jsp.forward(req, resp, Go.QUESTS, "Game not found");
        }
        //TODO !!!! Попробовать отправить на логин
        Jsp.forward(req, resp, Go.QUESTS, "Login to site please");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long gameId = Parser.getId(req);
        Long answerId = Parser.getId(req, Jsp.Key.ANSWER);
        Optional<GameDto> opGameDto = gameService.checkAnswer(gameId, answerId);
        if (opGameDto.isPresent()) {
            GameDto gameDto = opGameDto.get();
            sendNextStep(req, resp, gameDto);
            return;
        }
        Jsp.forward(req, resp, Go.QUESTS, "Game not found");
    }

    //TODO Why this need static??
    private static void sendNextStep(HttpServletRequest req, HttpServletResponse resp, GameDto gameDto) throws ServletException, IOException {
        req.setAttribute(Jsp.Key.GAME, gameDto);
        req.setAttribute(Jsp.Key.QUESTION, gameDto.getQuestion());
        Jsp.forward(req, resp, Go.GAME);
    }
}
