package boj.graph;

import java.io.*;
import java.util.*;

public class Boj11797 {

    public static List<Pair>[] edges = new ArrayList[1001];

    public static List<Integer> routes = new ArrayList<>();

    public static long[] dist = new long[1100];

    public static int[] route = new int[1100];

    public static int nCity;

    public static int nEdge;

    public static int src;

    public static int dest;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            nCity = Integer.parseInt(stringTokenizer.nextToken());
            nEdge = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());

            for (int i = 1; i <= nCity; i++) {
                edges[i] = new ArrayList<>();
            }

            for (int i = 0; i < nEdge; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                int c = Integer.parseInt(stringTokenizer.nextToken());

                edges[a].add(new Pair(b, c));
            }

            stringTokenizer = new StringTokenizer(reader.readLine());
            src = Integer.parseInt(stringTokenizer.nextToken());
            dest = Integer.parseInt(stringTokenizer.nextToken());

            dijkstra(src);

            writer.write(dist[dest] + "\n");
            writer.write(routes.size() + "\n");

            for (int i = routes.size() - 1; i >= 0; i--) {
                writer.write(routes.get(i) + " ");
            }
        }
    }

    public static void dijkstra(int src) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));
        dist[src] = 0;
        route[src] = 0;

        while (!pq.isEmpty()) {
            int node = pq.peek().node;
            long weight = pq.poll().weight;

            for (Pair p : edges[node]) {
                int nextNode = p.node;
                long nextWeight = p.weight + weight;

                if (dist[nextNode] > nextWeight) {
                    dist[nextNode] = nextWeight;
                    pq.add(new Pair(nextNode, nextWeight));
                    route[nextNode] = node;
                }
            }
        }

        int node = dest;

        while (node != 0) {
            routes.add(node);
            node = route[node];
        }
    }

    public static class Pair implements Comparable<Pair> {

        private final int node;

        private final long weight;

        Pair(int a, long b) {
            node = a;
            weight = b;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.weight, o.weight);
        }
    }

}



