package ru.yandex.practicum.catsgram.util.exception;

import ru.yandex.practicum.catsgram.model.User;

public class UserException extends RuntimeException {
    private final User user;

    public UserException(String message, User user) {
        super(message);
        this.user = user;
    }

}
