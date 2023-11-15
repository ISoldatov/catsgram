package ru.yandex.practicum.catsgram.util.exception;

import ru.yandex.practicum.catsgram.model.User;

public class UserAlreadyExistException extends UserException {
    public UserAlreadyExistException(User user) {
        super("User с адресом " + user.getEmail() + " уже существует.", user);
    }
}
