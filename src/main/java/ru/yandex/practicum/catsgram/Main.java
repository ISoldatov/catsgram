package ru.yandex.practicum.catsgram;

import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static List<Integer> posts = Arrays.asList(33, 22, 55, 11, 22, 99, 77, 88, 66);

    public static void main(String[] args) {
        posts.sort(Comparator.comparingInt(p -> p));
        System.out.println(posts);
        List<Integer> list = posts.stream().skip(3).collect(Collectors.toList());
        System.out.println(list);
        list= list.stream().limit(3).collect(Collectors.toList());
        System.out.println(list);

    }


}
