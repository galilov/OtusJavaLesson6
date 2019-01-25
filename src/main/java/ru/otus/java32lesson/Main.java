package ru.otus.java32lesson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    static String[] array = {"b", "hello", "c", "e", "d"};

    public static void main(String[] args) throws IOException {
        smallTask();
    }

    private static void smallTask() {
        Stream.of(83232, 2, 3, 278, 876, 24, 5, 1, 920, 77)
                .filter(n -> n % 3 == 0)
                .forEach(System.out::println);
    }


    private static void example1() {
        System.out.println("Example 1");

        Arrays.stream(array).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        Arrays.stream(array).forEach(s -> System.out.println(s));
        Arrays.stream(array).forEach(System.out::println);
        System.out.println(">>>>>");

        Stream.of(array).forEach(System.out::println);
        System.out.println(">>>>>");

        Arrays.stream(array).sorted().forEach(System.out::println);
        System.out.println(">>>>>");

        System.out.println("End of Example 1");
    }

    private static void example2() {
        System.out.println("Example 2");

        String s = Stream.of(array)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.joining(","));

        System.out.println(s);

        System.out.println("End of Example 2");
    }


    private static void example3() throws IOException {
        Map<Integer, String> translit;
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("translit.txt")) {
            Stream<String> lines = (new BufferedReader(new InputStreamReader(inputStream))).lines();

            Stream<String[]> arrayStream = lines.map(line -> line.split(","));

            translit = arrayStream.collect(Collectors.toMap(
                    pair -> (int) Character.toUpperCase(pair[0].charAt(0)),
                    pair -> pair[1])
            );
        }

        String myName = "Александр Галилов";

        IntStream intStream = myName.toUpperCase().chars();

        Stream<String> stringStream = intStream.mapToObj(new IntFunction<String>() {
            @Override
            public String apply(int key) {
                return translit.get(key);
            }
        });

        String translated = stringStream
                .map(s -> s.equals("_") ? " " : s.toUpperCase())
                .collect(Collectors.joining());

        System.out.println(translated);

    }
}
