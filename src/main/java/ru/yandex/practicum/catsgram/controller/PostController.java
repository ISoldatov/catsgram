package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;
import ru.yandex.practicum.catsgram.util.exception.PostException;
import ru.yandex.practicum.catsgram.util.exception.PostNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    public static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        log.debug("Добавлен post: {}", post.toString());
        return postService.create(post);
    }

    @GetMapping(value = "/posts/{postId}")
    public Optional<Post> findById(@PathVariable int postId) {
        Optional<Post> post = postService.findById(postId);
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post c id " + postId + " не найден");
        } else {
            return post;
        }
    }

    @GetMapping("/posts")
    public List<Post> findAll(@RequestParam(required = false, defaultValue = "desc") String sort,
                              @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "10") Integer size) {
        if (!sort.equals("desc") && !sort.equals("asc")) {
            throw new PostException("Параметр sort может быть только asc или desc/");
        }

        if(page < 0 || size <= 0){
            throw new IllegalArgumentException();
        }
        Integer from = page * size;


        return postService.findAll(sort, from, size);
    }
}
