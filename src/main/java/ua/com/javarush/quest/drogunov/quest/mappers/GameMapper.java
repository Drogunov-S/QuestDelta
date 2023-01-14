package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Game;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

public class GameMapper implements Mapper<Game, GameDTO> {
    private UserMapper userMapper = new UserMapper();
    private QuestMapper questMapper = new QuestMapper();
    private QuestionMapper questionMapper = new QuestionMapper();
    
    @Override
    public GameDTO parseEntity(Game entity) {
        return GameDTO.builder()
                .user(userMapper.parseEntity(entity.getUser()))
                .quest(questMapper.parseEntity(entity.getQuest()))
                .lastQuestion(questionMapper.parseEntity(entity.getLastQuestion()))
                .build();
    }
    
    @Override
    public List<GameDTO> parseEntityAll(Collection<Game> entity) {
        return null;
    }
    
    @Override
    public Game parseDto(GameDTO dto) {
        if (isNull(dto)) {
            return null;
        }
        Game game = new Game();
        game.setId(isNull(dto.getId()) ? null : dto.getId());
        game.setUser(isNull(dto.getUser()) ? null : userMapper.parseDto(dto.getUser()));
        game.setQuest(isNull(dto.getQuest()) ? null : questMapper.parseDto(dto.getQuest()));
        game.setLastQuestion(isNull(dto.getLastQuestion()) ? null : questionMapper.parseDto(dto.getLastQuestion()));
        return game;
    }
    
    @Override
    public List<GameDTO> parseDtoAll(Collection<Game> entity) {
        return null;
    }
}
