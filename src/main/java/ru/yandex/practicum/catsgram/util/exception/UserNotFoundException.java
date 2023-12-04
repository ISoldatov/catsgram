package ru.yandex.practicum.catsgram.util.exception;

import ru.yandex.practicum.catsgram.model.User;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(User user) {
        super("User не найден.", user);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
