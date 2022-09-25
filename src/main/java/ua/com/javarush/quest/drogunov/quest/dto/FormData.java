package ua.com.javarush.quest.drogunov.quest.dto;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FormData {
    private final Map<String, String[]> parameterMap;

    private FormData(HttpServletRequest request) {
        this.parameterMap = request.getParameterMap();
    }

    public static FormData of(HttpServletRequest request) {
        return new FormData(request);
    }

    //Method renamed with getValue in to getValue, I think it is name more understand.
    /**
    * @apiNote Method may be return null
    * */
    //TODO Непонятно что возвращается значения или наименования значений
    public String getValue(String name) {
        return parameterMap.getOrDefault(name, new String[1])[0];
    }

    public Long getId() {
        return parameterMap.containsKey("id")
                ? Long.valueOf(getValue("id"))
                : null;
    }

    @Override
    public String toString() {
        return parameterMap.entrySet()
                .stream()
                .map(pair -> pair.getKey() + "=" + Arrays.toString(pair.getValue()))
                .collect(Collectors.joining());
    }
}
