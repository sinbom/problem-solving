package boj.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj11437 {

    public static int vertexNum;

    public static List<List<Integer>> tree;

    public static int m;

    public static int[] depth;

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            vertexNum = Integer.parseInt(reader.readLine());
            tree = new ArrayList<>();

            for (int i = 0; i < vertexNum + 1; i++) {
                tree.add(new ArrayList<>());
            }

            for (int i = 0; i < vertexNum - 1; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                tree.get(a).add(b);
                tree.get(b).add(a);
            }

            depth = new int[vertexNum + 1];
            parent = new int[vertexNum + 1];
            m = Integer.parseInt(reader.readLine());

            dfs(1, 1);

            for (int i = 0; i < m; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                writer.write(lca(a, depth[a], b, depth[b]) + "\n");
            }
        }
    }

    public static int lca(int a, int a_depth, int b, int b_depth) {
        if (a_depth > b_depth) {
            while (a_depth != b_depth) {
                a_depth--;
                a = parent[a];
            }
        } else if (a_depth < b_depth) {
            while (a_depth != b_depth) {
                b_depth--;
                b = parent[b];
            }
        }


        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    public static void dfs(int from, int cnt) {
        depth[from] = cnt++;

        for (Integer next : tree.get(from)) {
            if (depth[next] == 0) {
                dfs(next, cnt);
                parent[next] = from;
            }
        }
    }
}


