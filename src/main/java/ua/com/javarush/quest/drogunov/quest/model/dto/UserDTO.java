package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ua.com.javarush.quest.drogunov.quest.model.entity.Role;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class UserDTO extends BaseEntityDTO {
    private String login;
    private String password;
    private Role role;
    
    @Override
    public String toString() {
        return "UserDTO{" +
                super.toString() +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                "} ";
    }
}
