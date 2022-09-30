package ua.com.javarush.quest.drogunov.quest.repository;

import ua.com.javarush.quest.drogunov.quest.entity.Game;

import java.util.Comparator;
import java.util.stream.Stream;

public class GameRepository extends AbstractRepository<Game> implements Repository<Game> {
    private static final GameRepository GAME_REPOSITORY = new GameRepository();

    public static GameRepository get() {
        return GAME_REPOSITORY;
    }

    private GameRepository() {
    }

    @Override
    public Stream<Game> find(Game pattern) {
        return getStreamValue()
                .filter(game -> isOk(pattern, game, Game::getId)
                        && isOk(pattern, game, Game::getUserId)
                        && isOk(pattern, game, Game::getQuestId)
                        && isOk(pattern, game, Game::getCurrentQuestionId)
                        && isOk(pattern, game, Game::getGameState)
                )
                .sorted(Comparator.comparing(Game::getGameState));
    }
}
