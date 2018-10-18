package ru.krivolutsky.work11.classes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    private int[][] graph = new int[][]{
            {0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0}
    };

    public int getSize() {
        return graph.length;
    }

    public void walkWide(Consumer<Integer> consumer) {
        Queue<Integer> queue = new LinkedList<>();
        if (graph.length != 0) {
            queue.add(0);
        }
        boolean[] visit = new boolean[graph.length];
        while (queue.peek() != null) {
            if (visit[queue.element()]) {
                queue.remove();
                continue;
            }
            for (int i = 0; i < graph.length; i++) {
                if (queue.element() != i) {
                    if (graph[queue.element()][i] == 1 && !visit[i]) {
                        queue.add(i);
                    }
                }
            }
            visit[queue.element()] = true;
            consumer.accept(queue.element());
            queue.remove();
        }
    }

    public void visit(int index, boolean[] visit, Consumer<Integer> consumer) {
        if (index < 0 || index >= graph.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы графа.");
        }
        if (visit[index]) {
            return;
        }
        consumer.accept(index);
        visit[index] = true;
        for (int i = 0; i < graph.length; i++) {
            if (i != index) {
                if (graph[index][i] == 1 && !visit[i]) {
                    visit(i, visit, consumer);
                }
            }
        }
    }
}