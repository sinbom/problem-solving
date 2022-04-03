package boj.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1197 {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int e = Integer.parseInt(stringTokenizer.nextToken());
            List<Edge> edges = new ArrayList<>();
            parent = new int[v + 1];

            for (int i = 0; i < e; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());
                int cost = Integer.parseInt(stringTokenizer.nextToken());

                edges.add(new Edge(start, end, cost));
            }

            Collections.sort(edges);

            for (int i = 1; i <= v; i++) {
                parent[i] = i;
            }

            int answer = 0;

            for (int i = 0; i < e; i++) {
                Edge edge = edges.get(i);

                if (!isSameParent(edge.s, edge.e)) {
                    union(edge.s, edge.e);
                    answer += edge.cost;
                }
            }

            writer.write(answer + "\n");
        }
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);

        return x == y;
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    public static class Edge implements Comparable<Edge> {

        int s, e, cost;

        Edge(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

}


