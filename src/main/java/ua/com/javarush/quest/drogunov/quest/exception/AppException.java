package ua.com.javarush.quest.drogunov.quest.exception;

public class AppException extends RuntimeException {
    private final String source;


    public AppException() {
        this("");
    }

    public AppException(String massage) {
        super(massage);
        StackTraceElement throwLine = this.getStackTrace()[2];
        source = "%s:%d".formatted(throwLine.getClassName(), throwLine.getLineNumber());
    }
}
