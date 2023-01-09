package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.drogunov.quest.model.entity.Role;

@Data
@Builder
public class UserDTO extends BaseEntityDTO {
    private String login;
    private String password;
    private Role role;
    
    
}
