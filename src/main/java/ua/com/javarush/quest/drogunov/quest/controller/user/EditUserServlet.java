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
import java.util.Objects;
import java.util.Optional;

import static ua.com.javarush.quest.drogunov.quest.util.Jsp.Key.ROLES;
import static ua.com.javarush.quest.drogunov.quest.util.Jsp.Key.USER;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.EDIT_USER)
public class EditUserServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkEditorInSession(req)) {
            Long id = Parser.getId(req);
            Optional<UserDto> user = userService.get(id);
            user.ifPresent(value -> req.setAttribute(USER, value));
            Jsp.forward(req, resp, Go.EDIT_USER);
        } else {
            req.setAttribute(Jsp.Key.USERS, userService.getAll());
            Jsp.forward(req, resp, Go.USERS, "Insufficient rights to edit");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormData formData = FormData.of(req);
        final String DEST = checkProfileEditor(req) ? Go.PROFILE : Go.USERS;
        if (checkEditorInSession(req)) {
            if (req.getParameter(Jsp.CRUD.UPDATE) != null) {
                userService.update(formData);
                imageService.uploadImage(req);
            } else if (req.getParameter(Jsp.CRUD.DELETE) != null) {
                userService.delete(formData);
            }
            Jsp.redirect(req,resp, DEST);
        } else {
            Jsp.forward(req,resp, DEST, "Insufficient rights to edit");
        }
    }

    @Override
    public void init() {
        getServletContext().setAttribute(ROLES, Role.values());
    }

    private boolean checkEditorInSession(HttpServletRequest req) {
        Long id = Parser.getId(req);
        Optional<UserDto> editor = Parser.getUser(req.getSession());
        return editor.isPresent() && (Objects.equals(editor.get().getId(), id));
    }

    static boolean checkProfileEditor(HttpServletRequest req) {
        Long id = Parser.getId(req);
        Optional<UserDto> editor = Parser.getUser(req.getSession());
        return editor.isPresent() && (Objects.equals(editor.get().getId(), id));
    }
}
