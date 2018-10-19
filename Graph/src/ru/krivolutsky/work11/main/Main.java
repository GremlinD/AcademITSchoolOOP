package ru.krivolutsky.work11.main;

import ru.krivolutsky.work11.classes.Graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(new int[][]{
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0}
        });
        Consumer<Integer> consumer = System.out::println;
        graph.walkWide(consumer);
        System.out.println();
        try {
            graph.visit(consumer);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
