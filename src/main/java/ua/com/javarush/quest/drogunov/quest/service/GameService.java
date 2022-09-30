package ua.com.javarush.quest.drogunov.quest.service;


import ua.com.javarush.quest.drogunov.quest.dto.FormData;
import ua.com.javarush.quest.drogunov.quest.dto.ui.GameDto;
import ua.com.javarush.quest.drogunov.quest.dto.ui.QuestionDto;
import ua.com.javarush.quest.drogunov.quest.entity.*;
import ua.com.javarush.quest.drogunov.quest.mapping.Mapper;
import ua.com.javarush.quest.drogunov.quest.repository.*;

import java.util.Optional;

public enum GameService {
    INSTANCE;

    private final Repository<Game> gameRepository = GameRepository.get();
    private final Repository<Quest> questRepository = QuestRepository.get();
    private final Repository<Question> questionRepository = QuestionRepository.get();
    private final Repository<Answer> answerRepository = AnswerRepository.get();

    private final QuestionService questionService = QuestionService.INSTANCE;

    public Optional<GameDto> getGame(FormData formData, Long userId) {
        Game gamePattern = Mapper.game.parse(formData);
        gamePattern.setUserId(userId);
        Optional<Game> currentGame = gameRepository
                .find(gamePattern)
                .findFirst();
        //TODO для чего final??
        final Game game;
        game = currentGame.orElseGet(() -> getNewGame(userId, gamePattern.getQuestId()));
        return fillGame(game);
    }

    public Optional<GameDto> checkAnswer(Long gameId, Long answerId) {
        Game game = gameRepository.get(gameId);
        if (game.getGameState() == GameState.PLAY) {
            Answer answer = answerRepository.get(answerId);
            Long nextQuestionId = answer.getNextQuestionId();
            game.setCurrentQuestionId(nextQuestionId);
            Question question = questionRepository.get(nextQuestionId);
            game.setGameState(question.getState());
            gameRepository.update(game);
        } else {
            game = getNewGame(game.getUserId(), game.getQuestId());
        }
        return fillGame(game);
    }

    private Game getNewGame(Long userID, Long questId) {
        Quest quest = questRepository.get(questId);
        Long startQuestionId = quest.getStartQuestionId();
        Question firstQuestion = questionRepository.get(startQuestionId);
        Game newGame = Game.with()
                .questId(questId)
                .currentQuestionId(startQuestionId)
                .gameState(firstQuestion.getState())
                .userId(userID)
                .build();
        gameRepository.create(newGame);
        return newGame;
    }

    private Optional<GameDto> fillGame(Game game) {
        Optional<GameDto> gameDto = Mapper.game.get(game);
        Optional<QuestionDto> questionDto = questionService.get(game.getCurrentQuestionId());
        gameDto.orElseThrow().setQuestion(questionDto.orElseThrow());
        return gameDto;
    }
}
