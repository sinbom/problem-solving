package boj.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1647 {

    public static int[] parent;

    public static List<Edge> edgeList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            edgeList = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());

                edgeList.add(new Edge(start, end, weight));
            }

            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            Collections.sort(edgeList);

            int answer = 0;
            int bigCost = 0;

            for (Edge edge : edgeList) {
                if (find(edge.start) != find(edge.end)) {
                    answer += edge.weight;
                    union(edge.start, edge.end);

                    bigCost = edge.weight;
                }
            }

            writer.write((answer - bigCost) + "\n");
        }
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    public static class Edge implements Comparable<Edge> {

        private final int start;

        private final int end;

        private final int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }

    }

}


