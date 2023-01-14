package ua.com.javarush.quest.drogunov.quest.service;

import ua.com.javarush.quest.drogunov.quest.mappers.GameMapper;
import ua.com.javarush.quest.drogunov.quest.mappers.Mapper;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Game;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;
import ua.com.javarush.quest.drogunov.quest.repository.GameRepository;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.repository.QuestionRepository;
import ua.com.javarush.quest.drogunov.quest.repository.UserRepository;

import java.util.List;

public class GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final QuestRepository questRepository;
    private final QuestionRepository questionRepository;
    
    
    private Mapper<Game, GameDTO> gameMapper = new GameMapper();
    
    public GameService(GameRepository gameRepository, UserRepository userRepository, QuestRepository questRepository, QuestionRepository questionRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.questRepository = questRepository;
        this.questionRepository = questionRepository;
    }
    
    public GameDTO createGuestGame(GameDTO gameDTO) {
        Game game = gameMapper.parseDto(gameDTO);
        User user = userRepository.getById(game.getUser().getId());
        Quest quest = questRepository.getById(game.getQuest().getId());
        game.setUser(user);
        game.setQuest(quest);
        game.setLastQuestion(quest.getQuestions().get(0));
        gameRepository.save(game);
        return gameMapper.parseEntity(game);
    }
}
