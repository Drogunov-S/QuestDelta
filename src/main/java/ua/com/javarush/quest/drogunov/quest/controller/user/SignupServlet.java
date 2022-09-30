package ua.com.javarush.quest.drogunov.quest.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.dto.FormData;
import ua.com.javarush.quest.drogunov.quest.dto.ui.UserDto;
import ua.com.javarush.quest.drogunov.quest.entity.Role;
import ua.com.javarush.quest.drogunov.quest.service.ImageService;
import ua.com.javarush.quest.drogunov.quest.service.UserService;
import ua.com.javarush.quest.drogunov.quest.util.Go;
import ua.com.javarush.quest.drogunov.quest.util.Jsp;
import ua.com.javarush.quest.drogunov.quest.util.Parser;

import java.io.IOException;
import java.util.Optional;

import static ua.com.javarush.quest.drogunov.quest.util.Go.SIGNUP;
import static ua.com.javarush.quest.drogunov.quest.util.Jsp.Key.ROLES;
import static ua.com.javarush.quest.drogunov.quest.util.Jsp.Key.USER;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(value = SIGNUP)
public class SignupServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;

    @Override
    public void init() {
        getServletContext().setAttribute(ROLES, Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Parser.getId(req);
        Optional<UserDto> optionalUserDto = userService.get(id);
        optionalUserDto.ifPresent(userDto -> req.setAttribute(USER, userDto));
        Jsp.forward(req, resp, Go.SIGNUP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.create(FormData.of(req));
        imageService.uploadImage(req);
        Jsp.redirect(req, resp, Go.USERS);
    }
}
