package boj.graph;

import java.io.*;
import java.util.*;

public class Boj1162 {

    public static int n;

    public static int m;

    public static int k;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] input = Arrays
                    .stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            n = input[0];
            m = input[1];
            k = input[2];

            List<List<Node>> road = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                road.add(new ArrayList<>());
            }

            for (int i = 1; i <= m; i++) {
                input = Arrays
                        .stream(reader.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

                road.get(input[0]).add(new Node(input[1], input[2], 0));
                road.get(input[1]).add(new Node(input[0], input[2], 0));
            }

            writer.write(findShortPath(1, road) + "");
        }

    }

    static long findShortPath(int start, List<List<Node>> road) {
        long[][] distance = new long[n + 1][k + 1];
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o.weight));

        distance[start][0] = 0;
        queue.add(new Node(start, 0, 0));

        for (int i = 0; i <= n; i++) {
            Arrays.fill(distance[i], Long.MAX_VALUE);
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.weight > distance[current.node][current.cnt]) {
                continue;
            }

            for (Node next : road.get(current.node)) {
                if (current.cnt < k && distance[next.node][current.cnt + 1] > distance[current.node][current.cnt]) {
                    distance[next.node][current.cnt + 1] = distance[current.node][current.cnt];
                    queue.add(new Node(next.node, distance[next.node][current.cnt + 1], current.cnt + 1));
                }

                if (distance[next.node][current.cnt] > distance[current.node][current.cnt] + next.weight) {
                    distance[next.node][current.cnt] = distance[current.node][current.cnt] + next.weight;
                    queue.add(new Node(next.node, distance[next.node][current.cnt], current.cnt));
                }
            }
        }

        return Arrays
                .stream(distance[n])
                .min()
                .getAsLong();
    }

    public static class Node {

        private final int node;

        private final int cnt;

        private final long weight;

        public Node(int node, long weight, int cnt) {
            this.node = node;
            this.cnt = cnt;
            this.weight = weight;
        }
    }
}




