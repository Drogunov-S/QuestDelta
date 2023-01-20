package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.AnswerDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestionDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Answer;
import ua.com.javarush.quest.drogunov.quest.model.entity.Game;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;

public class Mappers {
    public static final Mapper<Answer, AnswerDTO> answer = new AnswerMapper();
    public static final Mapper<Game, GameDTO> game = new GameMapper();
    public static final Mapper<Question, QuestionDTO> question = new QuestionMapper();
    public static final Mapper<Quest, QuestDTO> quest = new QuestMapper();
    public static final Mapper<User, UserDTO> user = new UserMapper();
}
