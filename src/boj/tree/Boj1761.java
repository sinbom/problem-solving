package boj.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1761 {

    public static int n;

    public static int h;

    public static List<Node>[] list;

    public static int[][] dp;

    public static int[] dis;

    public static int[] depth;


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            StringTokenizer stringTokenizer;

            list = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < n - 1; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(stringTokenizer.nextToken());
                int to = Integer.parseInt(stringTokenizer.nextToken());
                int w = Integer.parseInt(stringTokenizer.nextToken());

                list[from].add(new Node(to, w));
                list[to].add(new Node(from, w));
            }

            h = getTreeH();
            depth = new int[n + 1];
            dis = new int[n + 1];
            dp = new int[n + 1][h];

            init(1, 1, 0);
            fillParents();

            int m = Integer.parseInt(reader.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                int res = lca(a, b);
                sb.append(dis[a] + dis[b] - 2 * dis[res]).append("\n");

            }

            writer.write(sb.toString());
        }
    }

    public static int getTreeH() {
        return (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
    }

    public static void init(int cur, int h, int pa) {
        depth[cur] = h;

        for (Node nxt : list[cur]) {
            if (nxt.to != pa) {
                dis[nxt.to] = dis[cur] + nxt.w;
                init(nxt.to, h + 1, cur);
                dp[nxt.to][0] = cur;
            }
        }
    }

    public static void fillParents() {
        for (int i = 1; i < h; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
            }
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
                a = dp[a][i];
            }
        }

        if (a == b) return a;

        for (int i = h - 1; i >= 0; i--) {
            if (dp[a][i] != dp[b][i]) {
                a = dp[a][i];
                b = dp[b][i];
            }
        }
        return dp[a][0];
    }

    public static class Node {

        private final int to;

        private final int w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }


}


