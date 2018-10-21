package ru.krivolutsky.work11.classes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    private int[][] graph;

    public Graph(int[][] graph) {
        this.graph = graph;
    }

    public void walkWide(Consumer<Integer> consumer) {
        Queue<Integer> queue = new LinkedList<>();
        if (graph.length != 0) {
            queue.add(0);
        }
        boolean[] visit = new boolean[graph.length];
        int visitCount = 0;
        while (!queue.isEmpty() || visitCount < visit.length) {
            if (queue.isEmpty()) {
                for (int i = 1; i < visit.length; i++) {
                    if (!visit[i]) {
                        queue.add(i);
                        break;
                    }
                }
            }
            int element = queue.remove();
            if (visit[element]) {
                continue;
            }
            for (int i = 0; i < graph.length; i++) {
                if (element != i) {
                    if (graph[element][i] == 1 && !visit[i]) {
                        queue.add(i);
                    }
                }
            }
            visit[element] = true;
            visitCount++;
            consumer.accept(element);
        }
    }

    public void visit(Consumer<Integer> consumer) {
        boolean[] visit = new boolean[graph.length];
        int visitCount = 0;
        while (visitCount < visit.length) {
            for (int i = 0; i < visit.length; i++) {
                if (!visit[i]) {
                    visitCount = visit(i, visit, consumer, visitCount);
                    break;
                }
            }
        }
    }

    private int visit(int index, boolean[] visit, Consumer<Integer> consumer, int visitCount) {
        int count = visitCount;
        if (visit[index]) {
            return count;
        }
        consumer.accept(index);
        visit[index] = true;
        count++;
        for (int i = 0; i < graph.length; i++) {
            if (i != index) {
                if (graph[index][i] == 1 && !visit[i]) {
                    count = visit(i, visit, consumer, count);
                }
            }
        }
        return count;
    }
}