package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.util.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.util.exception.UserAlreadyExistException;

import java.util.*;

@Service
public class UserService {

    private final Set<User> users = new HashSet<>();

    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    public User create(User user) {
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

    public User update(User user) {
        String email = user.getEmail();
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new InvalidEmailException(user);
        }
        users.remove(user);
        users.add(user);
        return user;
    }

    public Optional<User> findUserByEmail(String author) {
        return users.stream().filter(u -> u.getEmail().equals(author)).findFirst();
    }

}
