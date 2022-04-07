package boj.graph;

import java.io.*;
import java.util.*;

public class Boj1753 {

    public static final int INF = 100000000;

    public static List<Node>[] list;

    public static int[] dist;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int e = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(reader.readLine());
            list = new ArrayList[v + 1];
            dist = new int[v + 1];

            for (int i = 1; i <= v; i++) {
                list[i] = new ArrayList<>();
            }

            Arrays.fill(dist, INF);

            for (int i = 0; i < e; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());

                list[start].add(new Node(end, weight));
            }

            StringBuilder sb = new StringBuilder();

            dijkstra(k, v);

            for (int i = 1; i <= v; i++) {
                if (dist[i] == INF) {
                    sb.append("INF\n");
                } else {
                    sb.append(dist[i]).append("\n");
                }
            }

            writer.write(sb.toString());
        }
    }

    private static void dijkstra(int start, int v) {
        Queue<Node> queue = new PriorityQueue<>();
        boolean[] check = new boolean[v + 1];

        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int cur = curNode.end;

            if (check[cur]) {
                continue;
            }

            check[cur] = true;

            for (Node node : list[cur]) {
                if (dist[node.end] > dist[cur] + node.weight) {
                    dist[node.end] = dist[cur] + node.weight;
                    queue.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {

        private final int end;

        private final int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

}



