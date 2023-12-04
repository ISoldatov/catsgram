package ru.yandex.practicum.catsgram.util.exception;

import ru.yandex.practicum.catsgram.model.User;

public class UserException extends RuntimeException {
    private User user;

    public UserException(String message, User user) {
        super(message);
        this.user = user;
    }

    public UserException(String message) {
        super(message);
    }
}
