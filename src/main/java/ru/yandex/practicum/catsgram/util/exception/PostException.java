package ru.yandex.practicum.catsgram.util.exception;

import ru.yandex.practicum.catsgram.model.Post;

public class PostException extends RuntimeException{
    private Post post;

    public PostException(String message) {
        super(message);
    }
}
