package ua.com.javarush.quest.drogunov.quest.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import lombok.SneakyThrows;
import ua.com.javarush.quest.drogunov.quest.util.Go;

@WebFilter(value = Go.ALL, initParams = @WebInitParam(name = "code", value = "UTF-8"))
public class Encoder implements Filter {

    String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("code");
    }

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        String characterEncoding = request.getCharacterEncoding();
        if (!code.equalsIgnoreCase(characterEncoding)) {
            request.setCharacterEncoding(code);
        }

        characterEncoding = response.getCharacterEncoding();
        if (!code.equalsIgnoreCase(characterEncoding)) {
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }
}
