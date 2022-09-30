package ua.com.javarush.quest.drogunov.quest.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.drogunov.quest.dto.ui.UserDto;
import ua.com.javarush.quest.drogunov.quest.exception.AppException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Parser {
    public String getCommand(HttpServletRequest req) {
        String uri = req.getRequestURI();
        Matcher matcher = Pattern.compile(".*(/[a-z-]*)").matcher(uri);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new AppException("Incorrect uri: " + uri);
        }
    }

    public Long getId(HttpServletRequest req) {
        return getId(req, Jsp.Key.ID);
    }

    public static Long getId(HttpServletRequest req, String key) {
        String id = req.getParameter(key);
        return id != null && !id.isBlank()
                ? Long.parseLong(id)
                : 0L;
    }

    public Long getId(HttpSession session) {
        Object user = session.getAttribute(Jsp.Key.USER);
        return user != null
                ? ((UserDto) user).getId()
                : 0L;
    }

    public Optional<UserDto> getUser(HttpSession session) {
        Object user = session.getAttribute(Jsp.Key.USER);
        return user != null
                ? Optional.of((UserDto) user)
                : Optional.empty();
    }


}
