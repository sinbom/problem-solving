package boj.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj11438 {

    private static int n;

    private static int m;

    private static int k;

    private static List<List<Integer>> tree;

    private static int[] depth;

    private static int[][] parents;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer;
            n = Integer.parseInt(reader.readLine());
            tree = new ArrayList<>();

            for (int i = 0; i < n + 1; i++) {
                tree.add(new ArrayList<>());
            }

            for (int i = 0; i < n - 1; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                tree.get(a).add(b);
                tree.get(b).add(a);
            }

            int tmp = 1;

            while (tmp <= n) {
                tmp <<= 1;
                k++;
            }

            depth = new int[n + 1];
            parents = new int[n + 1][k];

            dfs(1, 1);
            fillParents();

            m = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                int lca = lca(a, b);
                writer.write(lca + "\n");
            }
        }
    }

    private static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = k - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parents[a][i];
            }
        }

        if (a == b) return a;

        for (int i = k - 1; i >= 0; i--) {
            if (parents[a][i] != parents[b][i]) {
                a = parents[a][i];
                b = parents[b][i];
            }
        }

        return parents[a][0];
    }

    private static void fillParents() {
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                parents[j][i] = parents[parents[j][i - 1]][i - 1];
            }
        }
    }

    private static void dfs(int node, int cnt) {
        depth[node] = cnt;

        for (Integer next : tree.get(node)) {
            if (depth[next] == 0) {
                dfs(next, cnt + 1);
                parents[next][0] = node;
            }
        }
    }
}


