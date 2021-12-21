package etc.dp;

import java.io.*;
import java.util.*;

/**
 * 이것이 코딩 테스트다 Chapter 9, 다익스트라
 */
public class Etc2 {

    public static int[] distance;

    public static List<LinkedList<Node>> graph;

    public static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            graph = new ArrayList<>(n + 1);
            distance = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                graph.add(new LinkedList<>());
                distance[i] = INF;
            }

            for (int i = 1; i <= m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(stringTokenizer.nextToken());
                int to = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());

                graph
                        .get(from)
                        .add(new Node(to, weight));
            }

            dijkstra(c);

            int count = 0;
            int cost = 0;

            for (int i = 1; i <= n; i++) {
                if (distance[i] >= INF || i == c) {
                    continue;
                }
                count++;
                cost = Math.max(distance[i], cost);
            }

            writer.write(count + " " + cost);
        }

    }

    public static void dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        distance[start] = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (distance[current.number] < current.weight) {
                continue;
            }

            for (Node next : graph.get(current.number)) {
                if (distance[next.number] > current.weight + next.weight) {
                    distance[next.number] = current.weight + next.weight;
                    queue.add(new Node(next.number, distance[next.number]));
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        private final int number;
        private final int weight;

        public Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

}