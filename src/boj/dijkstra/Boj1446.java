package boj.dijkstra;

import java.io.*;
import java.util.*;

public class Boj1446 {

    public static class Node implements Comparable<Node> {

        public final int start;
        public final int end;
        public final int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    public static List<Node> nodes;

    public static int[] distance;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());

            nodes = new ArrayList<>(n);
            distance = new int[d + 1];

            for (int i = 0; i <= d; i++) {
                distance[i] = i;
            }

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());

                if (end > d || end - start <= weight) {
                    continue;
                }

                nodes.add(new Node(start, end, weight));
            }

            dijkstra(0, d);

            writer.write(distance[d] + "");
        }
    }

    public static void dijkstra(int start, int d) {
        Queue<Node> queue = new PriorityQueue<>();
        distance[start] = 0;
        queue.offer(new Node(start, 0, 0));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            for (Node node : nodes) {
                if (node.start >= poll.end) {
                    int diff = node.start - poll.end;

                    if (distance[node.end] > distance[poll.end] + node.weight + diff) {
                        distance[node.end] = distance[poll.end] + node.weight + diff;
                        queue.offer(new Node(poll.end, node.end, distance[node.end]));
                    }
                }
            }

            distance[d] = Math.min(distance[poll.end] + d - poll.end, distance[d]);
        }
    }

}