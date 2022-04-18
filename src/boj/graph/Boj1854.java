package boj.graph;

import java.io.*;
import java.util.*;

public class Boj1854 {

    public static int n;

    public static int m;

    public static int k;

    public static List<Node>[] edge;

    public static Queue<Integer>[] dist;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            k = Integer.parseInt(stringTokenizer.nextToken());
            dist = new PriorityQueue[n + 1];
            edge = new ArrayList[n + 1];

            for (int i = 0; i < n + 1; ++i) {
                dist[i] = new PriorityQueue<>(k);
                edge[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; ++i) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int u = Integer.parseInt(stringTokenizer.nextToken());
                int v = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());
                edge[u].add(new Node(v, weight));
            }

            dijkstra();

            for (int i = 1; i <= n; ++i) {
                if (dist[i].size() == k) {
                    writer.write(dist[i].peek() * -1 + "\n");
                } else {
                    writer.write("-1\n");
                }
            }
        }
    }

    private static void dijkstra() {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        dist[1].add(0);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next : edge[cur.v]) {
                if (dist[next.v].size() < k) {
                    dist[next.v].add((cur.weight + next.weight) * -1);
                    pq.add(new Node(next.v, cur.weight + next.weight));
                } else if ((dist[next.v].peek() * -1) > cur.weight + next.weight) {
                    dist[next.v].poll();
                    dist[next.v].add((cur.weight + next.weight) * -1);
                    pq.add(new Node(next.v, cur.weight + next.weight));
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {

        private final int v;

        private final int weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

}




