package ua.com.javarush.quest.drogunov.quest.service;

import ua.com.javarush.quest.drogunov.quest.exceptions.QuestionNotFoundException;
import ua.com.javarush.quest.drogunov.quest.mappers.Mappable;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.*;
import ua.com.javarush.quest.drogunov.quest.repository.GameRepository;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.repository.UserRepository;
import ua.com.javarush.quest.drogunov.quest.util.RepositoryFactory;

import java.util.List;

public class GameService {
    private final RepositoryFactory repositoryFactory = new RepositoryFactory();
    private final GameRepository gameRepository = repositoryFactory.getRepository(GameRepository.class);
    private final UserRepository userRepository = repositoryFactory.getRepository(UserRepository.class);
    private final QuestRepository questRepository = repositoryFactory.getRepository(QuestRepository.class);
    
    public GameDTO createGuestGame(GameDTO gameDTO) {
        Game game = Mappable.parse.from(gameDTO);
        User user = userRepository.getById(game.getUser().getId());
        Quest quest = questRepository.getById(game.getQuest().getId());
        game.setUser(user);
        game.setQuest(quest);
        game.setLastQuestion(quest.getQuestions().get(0));
        gameRepository.save(game);
        return Mappable.parse.from(game);
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
    }
    
    public boolean isTrueAnswer(Long targetAnswer, Long gameId) {
        Long trueAnswerId = gameRepository.getTrueAnswerId(gameId);
        return targetAnswer.equals(trueAnswerId);
    }
    
    public GameDTO getCurrentGame(GameDTO currentGame) {
        Game game = gameRepository.getById(currentGame.getId());
        return Mappable.parse.from(game);
    }
    
}
