package ua.com.javarush.quest.drogunov.quest.mapping;

import ua.com.javarush.quest.drogunov.quest.dto.FormData;

import java.util.Optional;
class GameMapper implements Mapper<Game, GameDto> {
    @Override
    public Optional<GameDto> get(Game game) {
        return game != null
                ? Optional.of(GameDto.with()
                .id(game.getId())
                .questId(game.getUserid())
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
