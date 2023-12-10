package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.util.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();

    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public Optional<Post> findById(int postId) {
        return posts.stream().filter(p -> p.getId() == postId).findFirst();
    }

    public Post create(Post post) {
        Optional<User> user = userService.findUserByEmail(post.getAuthor());
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден.");
        }
        posts.add(post);
        return post;
    }

    public List<Post> findAll(String sort, int from, int size) {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreationDate))
                .skip(from)
                .limit(size).collect(Collectors.toList());

    }


}