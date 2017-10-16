package com.taylor.jdk8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/31 15:16
 */
public class T {
    public static void main(String... args) {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(7),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        List<Integer> list = inputStream.flatMap(Collection::stream).parallel().sorted().collect(Collectors.toList());
        System.out.println(list);

        List<String> objects = Arrays.asList("a", "b", "c").stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(objects);

        List<Integer> obj = Arrays.asList(1, 2, 3).stream().map(n -> n << 8).collect(Collectors.toList());
        System.out.println(obj);

        Arrays.asList("a", "b", "d").forEach((String e) -> {
            System.out.print(e);
            System.out.print(e);
        });
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator));

        int length = Arrays.asList("a", "b", "d").stream().filter("0"::equals).toArray().length;
        System.out.println(length);
    }
}
