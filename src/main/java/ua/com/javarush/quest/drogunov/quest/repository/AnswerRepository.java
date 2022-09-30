package ua.com.javarush.quest.drogunov.quest.repository;

import ua.com.javarush.quest.drogunov.quest.entity.Answer;

import java.util.Comparator;
import java.util.stream.Stream;

public class AnswerRepository extends AbstractRepository<Answer> implements Repository<Answer> {

    private static final AnswerRepository ANSWER_REPOSITORY = new AnswerRepository();

    public static AnswerRepository get() {
        return ANSWER_REPOSITORY;
    }

    private AnswerRepository() {
    }

    @Override
    public Stream<Answer> find(Answer pattern) {
        return getStreamValue()
                .filter(answer -> isOk(pattern, answer, Answer::getId)
                        && isOk(pattern, answer, Answer::getQuestionId)
                        && isOk(pattern, answer, Answer::getText)
                )
                .sorted(Comparator.comparing(Answer::getId));
    }
}
