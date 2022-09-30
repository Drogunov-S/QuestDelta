package ua.com.javarush.quest.drogunov.quest.mapping;

import ua.com.javarush.quest.drogunov.quest.dto.FormData;
import ua.com.javarush.quest.drogunov.quest.dto.ui.GameDto;
import ua.com.javarush.quest.drogunov.quest.entity.Game;

import java.util.Optional;
class GameMapper implements Mapper<Game, GameDto> {
    @Override
    public Optional<GameDto> get(Game game) {
        return game != null
                ? Optional.of(GameDto.with()
                .id(game.getId())
                .questId(game.getUserId())
                .gameState(game.getGameState())
                .userId(game.getUserId())
                .build())
                : Optional.empty();
    }

    @Override
    public Game parse(FormData formData) {
        Game game = Game.with().build();
        return fill(game, formData);
    }
}
