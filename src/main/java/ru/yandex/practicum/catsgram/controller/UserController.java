package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.util.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.util.exception.UserAlreadyExistException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private Set<User> users = new HashSet<>();

    @GetMapping
    public List<User> getAll() {
        log.debug("Количество пользователей: {}",users.size());
        return new ArrayList<>(users);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        log.debug("Добавлен user: {}", user);
        String email = user.getEmail();
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new InvalidEmailException(user);
        }

        if (users.contains(user)) {
            throw new UserAlreadyExistException(user);
        } else {
            users.add(user);
        }

        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        String email = user.getEmail();
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new InvalidEmailException(user);
        }
        users.remove(user);
        users.add(user);
        return user;
    }

}
