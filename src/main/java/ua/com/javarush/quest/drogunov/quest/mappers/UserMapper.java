package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;

public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public UserDTO parseEntity(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .role(entity.getRole())
                .build();
    }
    
    @Override
    public List<UserDTO> parseEntityAll(Collection<User> entity) {
        return null;
    }
    
    @Override
    public User parseDto(UserDTO dto) {
        if (isNull(dto)) {
            return null;
        }
        User user = new User();
        user.setId(isNull(dto.getId()) ? null : dto.getId());
        user.setLogin(isNull(dto.getLogin()) ? null : dto.getLogin());
        user.setRole(isNull(dto.getRole()) ? null : dto.getRole());
        return user;
    }
    
    @Override
    public List<User> parseDtoAll(Collection<UserDTO> entity) {
        return null;
    }
}
