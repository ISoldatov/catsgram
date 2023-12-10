package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;
import ru.yandex.practicum.catsgram.util.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.util.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.util.exception.UserNotFoundException;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{email}")
    public Optional<User> findByEmail(String email) {
        Optional<User> user = userService.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User с email " + email + " не найден");
        } else {
            return user;
        }
    }

    @PostMapping
    public User create(@RequestBody User user) {
        log.debug("Добавлен user: {}", user);
        return userService.create(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

}
