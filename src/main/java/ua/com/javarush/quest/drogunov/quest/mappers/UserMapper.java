package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;

import java.util.Collection;
import java.util.List;

public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public UserDTO parseEntity(User entity) {
        return UserDTO.builder()
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
        return null;
    }
    
    @Override
    public List<UserDTO> parseDtoAll(Collection<User> entity) {
        return null;
    }
}
