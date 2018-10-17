package ru.krivolutsky.work11.main;

import ru.krivolutsky.work11.classes.Graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Consumer<Integer> consumer = System.out::println;
        graph.walkWide(consumer);
        System.out.println();
        try {
            graph.visit(0, new boolean[graph.getSize()], consumer);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
