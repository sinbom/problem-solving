package boj.graph;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj11724 {

    public static int[] nodes;

    public static int[] sizes;

    public static int root(int node) {
        while (node != nodes[node]) {
            nodes[node] = nodes[nodes[node]];
            node = nodes[node];
        }

        return node;
    }

    public static void union(int a, int b) {
        int aRoot = root(a);
        int bRoot = root(b);

        if (aRoot == bRoot) {
            return;
        }

        if (sizes[aRoot] < sizes[bRoot]) {
            nodes[aRoot] = bRoot;
            sizes[bRoot] += sizes[aRoot];
        } else {
            nodes[bRoot] = aRoot;
            sizes[aRoot] += sizes[bRoot];
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            nodes = new int[n + 1];
            sizes = new int[n + 1];

            for (int i = 1; i < nodes.length; i++) {
                nodes[i] = i;
                sizes[i] = 1;
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int u = Integer.parseInt(stringTokenizer.nextToken());
                int v = Integer.parseInt(stringTokenizer.nextToken());
                union(u, v);
            }

            Set<Integer> set = new HashSet<>(n);

            for (int i = 1; i < nodes.length; i++) {
                set.add(root(i));
            }

            writer.write(set.size() + "");
        }
    }

}