package boj.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15480 {

    public static int n;

    public static int h;

    public static List<Integer>[] list;

    public static int[] depth;

    public static int[][] parent;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            list = new ArrayList[n + 1];
            for (int i = 1; i < n + 1; i++) {
                list[i] = new ArrayList<>();
            }

            StringTokenizer st;
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }

            h = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
            parent = new int[n + 1][h];
            depth = new int[n + 1];

            initTree(1, 0);
            fillParents();

            StringBuilder sb = new StringBuilder();
            int m = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(reader.readLine());
                int r = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                sb.append(comparingDepth(lca(r, a), comparingDepth(lca(r, b), lca(a, b)))).append("\n");
            }

            writer.write(sb.toString() + "\n");
        }
    }

    public static int comparingDepth(int a, int b) {
        if (depth[a] > depth[b]) {
            return a;
        } else {
            return b;
        }
    }

    public static int lca(int a, int b) {
        int ah = depth[a];
        int bh = depth[b];
        if (ah < bh) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = h - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[a][i];
            }
        }

        if (a == b) return a;

        for (int i = h - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }

    public static void initTree(int idx, int pa) {
        for (int nxt : list[idx]) {
            if (nxt != pa) {
                depth[nxt] = depth[idx] + 1;
                initTree(nxt, idx);
                parent[nxt][0] = idx;
            }
        }
    }

    public static void fillParents() {
        for (int i = 1; i < h; i++) {
            for (int j = 1; j < n + 1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }
}


