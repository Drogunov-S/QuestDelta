package ua.com.javarush.quest.drogunov.quest.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.drogunov.quest.dto.ui.UserDto;
import ua.com.javarush.quest.drogunov.quest.entity.Role;
import ua.com.javarush.quest.drogunov.quest.util.Jsp;
import ua.com.javarush.quest.drogunov.quest.util.Parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ua.com.javarush.quest.drogunov.quest.util.Go.*;

@WebFilter()
public class RoleSelector implements Filter {

    private final Map<Role, List<String>> uriMap = Map.of(
            Role.GUEST, List.of(ROOT, USERS, LOGIN, SIGNUP, GAME),
            Role.USER, List.of(ROOT, USERS, LOGIN, SIGNUP, PROFILE, LOGOUT, EDIT_USER, GAME),
            Role.ADMIN, List.of(ROOT, USERS, LOGIN, SIGNUP, PROFILE, LOGOUT, EDIT_USER, GAME)
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Object user = req.getSession().getAttribute(Jsp.Key.USER);
        Role role = (Objects.isNull(user))
                ? Role.GUEST
                : ((UserDto) user).getRole();
        String command = Parser.getCommand(req);
        if (uriMap.get(role).contains(command)) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            Jsp.redirect(req, resp, ROOT);
        }
    }
}
