package ru.krivolutsky.work11.main;

import ru.krivolutsky.work11.classes.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.walkWide();
        System.out.println();
        try {
            graph.Visit(0);
        }catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
