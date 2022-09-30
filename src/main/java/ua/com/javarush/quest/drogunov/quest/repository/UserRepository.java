package ua.com.javarush.quest.drogunov.quest.repository;

import ua.com.javarush.quest.drogunov.quest.entity.User;

import java.util.Comparator;
import java.util.stream.Stream;

public class UserRepository extends AbstractRepository<User> implements Repository<User> {
    private static final UserRepository USER_REPOSITORY = new UserRepository();

    public static UserRepository get() {
        return USER_REPOSITORY;
    }

    private UserRepository() {
    }

    @Override
    public Stream<User> find(User pattern) {
        return getStreamValue()
                .filter(user -> isOk(pattern, user, User::getId)
                        && isOk(pattern, user, User::getLogin)
                        && isOk(pattern, user, User::getPassword)
                        && isOk(pattern, user, User::getRole)
                )
                .sorted(Comparator.comparing(User::getLogin));
    }
}
