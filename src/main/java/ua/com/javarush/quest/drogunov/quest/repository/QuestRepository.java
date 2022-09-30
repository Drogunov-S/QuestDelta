package ua.com.javarush.quest.drogunov.quest.repository;

import ua.com.javarush.quest.drogunov.quest.entity.Quest;

import java.util.Comparator;
import java.util.stream.Stream;

public class QuestRepository extends AbstractRepository<Quest> implements Repository<Quest> {

    private static final QuestRepository QUEST_REPOSITORY = new QuestRepository();

    public static QuestRepository get() {
        return QUEST_REPOSITORY;
    }

    private QuestRepository() {
    }

    @Override
    public Stream<Quest> find(Quest pattern) {
        return getStreamValue()
                .filter(quest -> isOk(pattern, quest, Quest::getId)
                        && isOk(pattern, quest, Quest::getAuthorId)
                        && isOk(pattern, quest, Quest::getQuestions)
                        && isOk(pattern, quest, Quest::getName)
                )
                .sorted(Comparator.comparing(Quest::getId));
    }
}
