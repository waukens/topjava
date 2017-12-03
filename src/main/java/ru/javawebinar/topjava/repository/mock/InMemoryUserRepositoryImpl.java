package ru.javawebinar.topjava.repository.mock;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();

    {
        List<User> userList = Arrays.asList(
                new User(null, "Misha", "123456q@mail.ru", "123456q", Role.ROLE_USER),
                new User(null, "Anna", "654321q@mail.ru", "654321q", Role.ROLE_USER),
                new User(null, "userName2", "email", "password", Role.ROLE_ADMIN)
        );
        userList.forEach(this::save);

    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.getAndIncrement());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        if (repository.containsKey(id)) {
            return repository.get(id);
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        if (repository.isEmpty()) {
            return Collections.emptyList();
        } else {
            return repository
                    .values()
                    .stream()
                    .sorted(Comparator.comparing(User::getName))
                    .collect(Collectors.toList());
        }

    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository
                .values()
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .get();
    }
}
