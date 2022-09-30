package ua.com.javarush.quest.drogunov.quest.dto.ui;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.javarush.quest.drogunov.quest.entity.Role;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class UserDto {
    private Long id;
    private String login;
    private String image;
    private Role role;
}
