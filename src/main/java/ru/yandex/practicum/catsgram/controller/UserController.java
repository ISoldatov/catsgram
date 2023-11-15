package ru.yandex.practicum.catsgram.controller;

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

    private Set<User> users = new HashSet<>();

    @GetMapping
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @PostMapping
    public User create(@RequestBody User user) {
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
