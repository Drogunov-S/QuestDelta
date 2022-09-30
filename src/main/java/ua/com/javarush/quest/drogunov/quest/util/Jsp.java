package ua.com.javarush.quest.drogunov.quest.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class Jsp {
    public final String WEB_INF_JSP = "WEB-INF%s.jsp";

    @UtilityClass
    public class Key {
        public final String ID = "id";

        public final String USER = "user";
        public final String USERS = "users";

        public final String ROLES = "roles";

        public final String QUEST = "quest";
        public final String QUESTS = "quests";
        public final String QUESTION = "question";

        public final String ERROR_MASSAGE = "errorMassage";
        public final String GAME = "game";
        public final String ANSWER = "answer";

    }

    @UtilityClass
    public class CRUD {
        public final String UPDATE = "update";
        public final String DELETE = "delete";
    }

    public void forward(HttpServletRequest req, HttpServletResponse resp, String uri)
            throws ServletException, IOException {
        String path = WEB_INF_JSP.formatted(uri);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    public void forward(HttpServletRequest req, HttpServletResponse resp, String uri, String errorMassage)
            throws ServletException, IOException {
        req.setAttribute(Key.ERROR_MASSAGE, errorMassage);
        forward(req, resp, uri);
    }

    public void redirect(HttpServletRequest req, HttpServletResponse resp, String uri) throws IOException {
        resp.sendRedirect(req.getContextPath() + uri);
    }

}
