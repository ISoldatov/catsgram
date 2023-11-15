package ru.yandex.practicum.catsgram.util.exception;

import ru.yandex.practicum.catsgram.model.User;

public class InvalidEmailException extends UserException {
    public InvalidEmailException(User user) {
        super("Адрес электронной почты содержит ошибку или пуст: " + user.getEmail(), user);
    }
}
