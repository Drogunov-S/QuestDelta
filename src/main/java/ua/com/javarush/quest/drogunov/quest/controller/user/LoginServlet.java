package ua.com.javarush.quest.drogunov.quest.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.dto.FormData;
import ua.com.javarush.quest.drogunov.quest.dto.ui.UserDto;
import ua.com.javarush.quest.drogunov.quest.service.UserService;
import ua.com.javarush.quest.drogunov.quest.util.Go;
import ua.com.javarush.quest.drogunov.quest.util.Jsp;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = Go.LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, Go.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<UserDto> optionalUserDto = userService.find(FormData.of(req));
        if (optionalUserDto.isPresent()) {
            req.getSession().setAttribute(Jsp.Key.USER, optionalUserDto.get());
            Jsp.redirect(req, resp, Go.PROFILE);
        } else {
            Jsp.forward(req, resp, Go.LOGIN, "User not found");
        }
    }
}
