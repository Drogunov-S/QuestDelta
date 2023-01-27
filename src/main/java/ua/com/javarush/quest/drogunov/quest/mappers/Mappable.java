package ua.com.javarush.quest.drogunov.quest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.com.javarush.quest.drogunov.quest.model.dto.*;
import ua.com.javarush.quest.drogunov.quest.model.entity.*;

import java.util.Collection;
import java.util.List;

@Mapper
public interface Mappable {
    Mappable parse = Mappers.getMapper(Mappable.class);
    
    UserDTO from(User user);
    
    QuestDTO from(Quest quest);
    
    List<QuestDTO> from(Collection<Quest> quest);
    
    QuestionDTO from(Question question);
    
    AnswerDTO from(Answer answer);
    
    GameDTO from(Game game);
    
    User from(UserDTO dto);
    
    Quest from(QuestDTO quest);
    
    Question from(QuestionDTO question);
    
    Answer from(AnswerDTO answer);
    
    Game from(GameDTO game);
    
    
}
