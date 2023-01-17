package ua.com.javarush.quest.drogunov.quest.exceptions;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(String s) {
        super(s);
    }
}
