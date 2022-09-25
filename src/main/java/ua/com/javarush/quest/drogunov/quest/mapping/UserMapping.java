package ua.com.javarush.quest.drogunov.quest.mapping;

import ua.com.javarush.quest.drogunov.quest.dto.FormData;

import java.util.Optional;

class UserMapping implements Mapper<User, UserDto> {


    @Override
    public Optional<UserDto> get(User user) {
        return user != null
                ? Optional.of(User.with()
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
