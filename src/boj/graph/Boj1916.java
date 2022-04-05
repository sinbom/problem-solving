package boj.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Boj1916 {

    public static int n;

    public static int m;

    public static List<ArrayList<Node>> a; // 인접리스트.

    public static int[] dist; // 시작점에서 각 정점으로 가는 최단거리.

    public static boolean[] check; // 방문 확인.

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            m = Integer.parseInt(reader.readLine());
            a = new ArrayList<>();
            dist = new int[n + 1];
            check = new boolean[n + 1];

            Arrays.fill(dist, Integer.MAX_VALUE);

            for (int i = 0; i <= n; i++) {
                a.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());

                a.get(start).add(new Node(end, weight));
            }

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int startPos = Integer.parseInt(stringTokenizer.nextToken());
            int endPos = Integer.parseInt(stringTokenizer.nextToken());

            writer.write(dijkstra(startPos, endPos) + "\n");
        }
    }

    public static int dijkstra(int start, int end) {
        Queue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[n + 1];

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;

            if (!check[cur]) {
                check[cur] = true;

                for (Node node : a.get(cur)) {
                    if (!check[node.end] && dist[node.end] > dist[cur] + node.weight) {
                        dist[node.end] = dist[cur] + node.weight;
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


