package imedevo.repository;

import imedevo.model.User;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

    private Map<Integer, User> users = new ConcurrentHashMap<Integer, User>() {{
        put(0, new User(0, "Tom", "324324", "weewrewr@test.ru", 1992));
        put(1, new User(1, "Shon", "532532", "weasdas@test.ru", 1991));
        put(2, new User(2, "Misha", "6435", "wezxc@test.ru", 1934));
        put(3, new User(3, "Katya", "234324", "wedfhdf@test.ru", 1956));
        put(4, new User(4, "Dima", "345345", "wehhgfh@test.ru", 1976));

    }};
    private AtomicInteger counter = new AtomicInteger(1);


    public Collection<User> findAll() {
        return users.values();
    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }

    public User save(User user) {

        if (user.getId() == null) {
            user.setId(counter.incrementAndGet());
        }

        users.put(user.getId(), user);

        return user;
    }

    public Optional<User> delete(Integer id) {
        return Optional.ofNullable(users.remove(id));
    }
}