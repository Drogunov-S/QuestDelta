package ua.com.javarush.quest.drogunov.quest;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.repository.GameRepository;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.repository.QuestionRepository;
import ua.com.javarush.quest.drogunov.quest.repository.UserRepository;
import ua.com.javarush.quest.drogunov.quest.service.GameService;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;
import ua.com.javarush.quest.drogunov.quest.util.RepositoryFactory;

public class NullPointer {
    public static void main(String[] args) {
        GameDTO gameDTO = GameDTO.builder()
                .user(UserDTO.builder()
                        .id(1L)
                        .build())
                .quest(QuestDTO.builder()
                        .id(2L)
                        .build())
                .build();
        SessionFactory sessionFactory = DbSession.getSessionFactory();
        RepositoryFactory repositoryFactory = new RepositoryFactory(sessionFactory);
        GameRepository gameRepository = repositoryFactory.getRepository(GameRepository.class);
        UserRepository userRepository = repositoryFactory.getRepository(UserRepository.class);
        QuestRepository questRepository = repositoryFactory.getRepository(QuestRepository.class);
        QuestionRepository questionRepository = repositoryFactory.getRepository(QuestionRepository.class);
        GameService gameService = new GameService(gameRepository, userRepository, questRepository, questionRepository);
        GameDTO guestGame = gameService.createGuestGame(gameDTO);
        System.out.println("//////////////");
        System.out.println(guestGame);
        System.out.println("//////////////");
    }
}
