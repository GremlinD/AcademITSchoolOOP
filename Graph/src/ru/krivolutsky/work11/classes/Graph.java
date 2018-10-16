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

    public void walkWide(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visit[0] = true;
        while (queue.peek() != null){
            for (Integer i = 0; i < graph[queue.element()]; i++) {

            }
        }
    }
}
