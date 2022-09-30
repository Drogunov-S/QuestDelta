package ua.com.javarush.quest.drogunov.quest.mapping;

import ua.com.javarush.quest.drogunov.quest.dto.FormData;
import ua.com.javarush.quest.drogunov.quest.dto.ui.UserDto;
import ua.com.javarush.quest.drogunov.quest.entity.User;

import java.util.Optional;

class UserMapper implements Mapper<User, UserDto> {


    @Override
    public Optional<UserDto> get(User user) {
        return user != null
                ? Optional.of(UserDto.with()
                .id(user.getId())
                .login(user.getLogin())
                .image(user.getImage())
                .role(user.getRole())
                .build())
                : Optional.empty();
    }

    @Override
    public User parse(FormData formData) {
        User user = User.with().build();
        return fill(user, formData);
    }
}
