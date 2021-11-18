package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Boj1697 {

    public static int[] graph;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            graph = new int[100001];

            bfs(n, k);
            writer.write(graph[k] + "");
        }
    }

    public static void bfs(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            if (node == k) {
                return;
            }

            int[] values = new int[]{
                    node + 1,
                    node - 1,
                    node * 2
            };

            for (int value : values) {
                if (value >= 0 && value <= 100000 && graph[value] == 0) {
                    queue.offer(value);
                    graph[value] = graph[node] + 1;
                }
            }
        }
    }

}