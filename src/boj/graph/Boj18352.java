package boj.graph;

import java.io.*;
import java.util.*;

public class Boj18352 {

    public static class Node implements Comparable<Node> {

        public final int node;
        public final int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    public static List<LinkedList<Node>> graph;

    public static int[] distance;

    public static List<Integer> results;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int x = Integer.parseInt(stringTokenizer.nextToken());

            graph = new ArrayList<>(n + 1);
            distance = new int[n + 1];
            results = new ArrayList<>(n + 1);
            Arrays.fill(distance, Integer.MAX_VALUE);

            for (int i = 0; i <= n; i++) {
                graph.add(new LinkedList<>());
            }

            for (int i = 1; i <= m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());
                graph
                        .get(start)
                        .add(new Node(end, 1));
            }

            dijkstra(x, k);

            Collections.sort(results);

            if (results.isEmpty()) {
                writer.write("-1");
            } else {
                for (Integer node : results) {
                    writer.write(node + "\n");
                }
            }
        }
    }

    public static void dijkstra(int start, int k) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        distance[start] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (distance[node.node] < node.weight) {
                continue;
            }

            if (distance[node.node] == k) {
                results.add(node.node);
            }

            for (Node link : graph.get(node.node)) {
                if (distance[link.node] > node.weight + link.weight) {
                    distance[link.node] = node.weight + link.weight;
                    queue.add(new Node(link.node, distance[link.node]));
                }
            }
        }
    }

}