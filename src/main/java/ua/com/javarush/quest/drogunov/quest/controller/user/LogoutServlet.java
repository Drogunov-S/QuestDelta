package ua.com.javarush.quest.drogunov.quest.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.drogunov.quest.util.Go;
import ua.com.javarush.quest.drogunov.quest.util.Jsp;

import java.io.IOException;

@WebServlet(value = Go.LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        Jsp.redirect(req, resp, Go.ROOT);
    }
}
