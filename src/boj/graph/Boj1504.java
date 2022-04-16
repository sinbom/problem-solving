package boj.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Boj1504 {

    public static int n;

    public static int e;

    public static List<List<Node>> a;

    public static int[] dist;

    public static boolean[] check;

    public static final int INF = 200000000;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            e = Integer.parseInt(stringTokenizer.nextToken());
            a = new ArrayList<>();
            dist = new int[n + 1];
            check = new boolean[n + 1];

            Arrays.fill(dist, INF);

            for (int i = 0; i <= n; i++) {
                a.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());

                a.get(start).add(new Node(end, weight));
                a.get(end).add(new Node(start, weight));
            }

            stringTokenizer = new StringTokenizer(reader.readLine());
            int v1 = Integer.parseInt(stringTokenizer.nextToken());
            int v2 = Integer.parseInt(stringTokenizer.nextToken());

            int res1 = 0;
            res1 += dijkstra(1, v1);
            res1 += dijkstra(v1, v2);
            res1 += dijkstra(v2, n);

            int res2 = 0;
            res2 += dijkstra(1, v2);
            res2 += dijkstra(v2, v1);
            res2 += dijkstra(v1, n);

            int ans = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);

            writer.write(ans + "\n");
        }
    }

    public static int dijkstra(int start, int end) {
        Queue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[n + 1];

        Arrays.fill(dist, INF);
        Arrays.fill(check, false);

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int current = curNode.end;

            if (!check[current]) {
                check[current] = true;

                for (Node node : a.get(current)) {
                    if (!check[node.end] && dist[node.end] > dist[current] + node.weight) {
                        dist[node.end] = dist[current] + node.weight;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        return dist[end];
    }

    public static class Node implements Comparable<Node> {

        private final int end;

        private final int weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }

    }

}




