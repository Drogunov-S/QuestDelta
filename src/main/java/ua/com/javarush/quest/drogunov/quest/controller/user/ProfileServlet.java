package ua.com.javarush.quest.drogunov.quest.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.entity.Role;
import ua.com.javarush.quest.drogunov.quest.service.UserService;
import ua.com.javarush.quest.drogunov.quest.util.Go;
import ua.com.javarush.quest.drogunov.quest.util.Jsp;
import ua.com.javarush.quest.drogunov.quest.util.Parser;

import java.io.IOException;

import static ua.com.javarush.quest.drogunov.quest.util.Jsp.Key.USER;

@WebServlet(value = Go.PROFILE)
@MultipartConfig(fileSizeThreshold = 1 << 20)
public class ProfileServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("roles", Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Parser.getId(req.getSession());
        userService.get(id)
                .ifPresent(userDto -> req.setAttribute(USER, userDto));
        Jsp.forward(req, resp, Go.PROFILE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (EditUserServlet.checkProfileEditor(req)) {
            Jsp.redirect(req, resp, Go.EDIT_USER + "?id=" + Parser.getId(req));
        } else {
            Jsp.forward(req, resp, Go.PROFILE, "Insufficient rights to edit");
        }
    }
}
