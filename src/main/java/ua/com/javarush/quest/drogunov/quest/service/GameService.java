package ua.com.javarush.quest.drogunov.quest.service;

import ua.com.javarush.quest.drogunov.quest.exceptions.QuestionNotFoundException;
import ua.com.javarush.quest.drogunov.quest.mappers.GameMapper;
import ua.com.javarush.quest.drogunov.quest.mappers.Mapper;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestionDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.*;
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
    
    public void updateGameData(GameDTO gameDTO) {
        Game game = gameRepository.getById(gameDTO.getId());
        List<Question> questions = game.getQuest().getQuestions();
        int i = questions.indexOf(game.getLastQuestion());
        if (i < 0) {
            throw new QuestionNotFoundException("Question not found in quest.");
        } else if (i == questions.size() - 1) {
            game.setLastQuestion(questions.get(i));
            game.setGameState(GameState.END);
        } else {
            game.setLastQuestion(questions.get(i + 1));
            game.setGameState(GameState.PLAY);
        }
        gameRepository.update(game);
//        update(game);
    }
    
    public boolean isTrueAnswer(Long targetAnswer, Long gameId) {
        Long trueAnswerId = gameRepository.getTrueAnswerId(gameId);
        return targetAnswer.equals(trueAnswerId);
    }
    /*
    public boolean isTrueAnswer(GameDTO game, Long userAnswerId) {
        return game.getLastQuestion().getTrueAnswer().getId().equals(userAnswerId);
    }*/
    
    private GameDTO update(GameDTO gameDTO) {
        Game game = gameMapper.parseDto(gameDTO);
        Game updateGame = gameRepository.update(game);
        GameDTO gameDTO1 = gameMapper.parseEntity(updateGame);
        return gameDTO1;
    }
    
    public GameDTO getCurrentGame(GameDTO currentGame) {
        Game game = gameRepository.getById(currentGame.getId());
        return gameMapper.parseEntity(game);
    }
    
}
