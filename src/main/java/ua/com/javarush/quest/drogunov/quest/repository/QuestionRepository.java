package ua.com.javarush.quest.drogunov.quest.repository;

import ua.com.javarush.quest.drogunov.quest.entity.Question;

import java.util.Comparator;
import java.util.stream.Stream;

public class QuestionRepository extends AbstractRepository<Question> implements Repository<Question> {

    private static final QuestionRepository QUESTION_REPOSITORY = new QuestionRepository();

    public static QuestionRepository get() {
        return QUESTION_REPOSITORY;
    }

    private QuestionRepository() {
    }

    @Override
    public Stream<Question> find(Question pattern) {
        return getStreamValue()
                .filter(question -> isOk(pattern, question, Question::getId)
                        && isOk(pattern, question, Question::getId)
                        && isOk(pattern, question, Question::getAnswers)
                        && isOk(pattern, question, Question::getText)
                        && isOk(pattern, question, Question::getImage)
                )
                .sorted(Comparator.comparing(Question::getId));
    }
}
