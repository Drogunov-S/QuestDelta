package ua.com.javarush.quest.drogunov.quest;

import org.hibernate.Session;
import ua.com.javarush.quest.drogunov.quest.entity.*;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;

import java.util.List;
import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        User user = new User();
        user.setRole(Role.USER);
        user.setLogin("first user");
        user.setPassword("qwerty");
        Question question1 = new Question();
        question1.setQuestion("First Question");
        Answer answer1 = new Answer();
        answer1.setAnswer("First answer");
        Answer answer2 = new Answer();
        answer2.setAnswer("Second answer");
        Answer answer3 = new Answer();
        answer3.setAnswer("Third answer");
        question1.setAnswers(Set.of(answer1, answer2, answer3));
        question1.setTrueAnswer(answer1);
        Answer answer4 = new Answer();
        answer4.setAnswer("Four answer");
        Answer answer5 = new Answer();
        answer5.setAnswer("Five answer");
        Answer answer6 = new Answer();
        answer6.setAnswer("Second answer");
        Question question2 = new Question();
        question2.setQuestion("Two Question");
        question2.setAnswers(Set.of(answer4, answer5, answer6));
        question2.setTrueAnswer(answer4);
        Quest quest = new Quest();
        quest.setAuthor(user);
        quest.setName("First quest");
        quest.setQuestions(List.of(question1, question2));
        Game game = new Game();
        game.setQuest(quest);
        game.setUser(user);
        game.setLastQuestion(question1);
        System.out.println("////////////".repeat(20));
        System.out.println(game);
        System.out.println("////////////".repeat(20));
        Session currentSession = DbSession.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();
        currentSession.save(user);
        currentSession.save(answer1);
        currentSession.save(answer2);
        currentSession.save(answer3);
        currentSession.save(question1);
        currentSession.save(answer4);
        currentSession.save(answer5);
        currentSession.save(answer6);
        currentSession.save(question2);
        currentSession.save(quest);
        currentSession.save(game);
        currentSession.getTransaction().commit();
    
    }
    
    public static Set<Answer> getAnswer1() {
        Answer answer1 = new Answer();
        answer1.setAnswer("First answer");
        Answer answer2 = new Answer();
        answer2.setAnswer("Second answer");
        Answer answer3 = new Answer();
        answer3.setAnswer("Third answer");
        return Set.of(answer1, answer2, answer3);
    }
    
    public static Set<Answer> getAnswer2() {
        Answer answer1 = new Answer();
        answer1.setAnswer("Four answer");
        Answer answer2 = new Answer();
        answer2.setAnswer("Five answer");
        Answer answer3 = new Answer();
        answer3.setAnswer("Second answer");
        return Set.of(answer1, answer2, answer3);
    }
    
}
