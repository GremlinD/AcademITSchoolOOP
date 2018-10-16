package ru.krivolutsky.work11.classes;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private byte[][] graph = new byte[][]{
            {0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0}
    };
    private boolean visit[] = new boolean[graph.length];

    public void walkWide() {
        Queue<Integer> queue = new LinkedList<>();
        if (graph.length != 0) {
            queue.add(0);
        }
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
            System.out.println(queue.element());
            queue.remove();
        }
        visit = new boolean[graph.length];
    }

    public void Visit(int index) {
        if (graph.length == 0) {
            
        }
        if (index < 0 || index >= graph.length) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы графа.");
        }
        if (visit[index]) {
            return;
        }
        System.out.println(index);
        visit[index] = true;
        for (int i = 0; i < graph.length; i++) {
            if (i != index) {
                if (graph[index][i] == 1 && !visit[i]) {
                    Visit(i);
                }
            }
        }
    }
}
