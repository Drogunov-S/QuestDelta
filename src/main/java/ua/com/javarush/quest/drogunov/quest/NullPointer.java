package ua.com.javarush.quest.drogunov.quest;

import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.service.GameService;

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
        GameService gameService = new GameService();
        GameDTO guestGame = gameService.createGuestGame(gameDTO);
        System.out.println("//////////////");
        System.out.println(guestGame);
        System.out.println("//////////////");
    }
}
