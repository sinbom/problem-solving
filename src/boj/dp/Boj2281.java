package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2281 {

    public static int[][] dp;

    public static int[] len;

    public static int n;

    public static int m;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            len = new int[n];
            dp = new int[n][m];

            for (int i = 0; i < n; i++) {
                len[i] = Integer.parseInt(reader.readLine());
                for (int j = 0; j < m; j++) {
                    dp[i][j] = -1;
                }
            }

            writer.write(go(0, 0) + "");
        }
    }

    public static int go(int np, int sp) {
        if (dp[np][sp] == -1) {
            if (sp == 0) {
                if (len[np] + 1 < m) {
                    if (np + 1 < n) {
                        dp[np][sp] = go(np + 1, len[np] + 1);
                    } else {
                        dp[np][sp] = 0;
                    }
                } else {
                    if (np + 1 < n) {
                        dp[np][sp] = go(np + 1, 0) + (m - len[np]) * (m - len[np]);
                    } else {
                        dp[np][sp] = 0;
                    }
                }
            } else {
                if (len[np] + sp + 1 < m) {
                    if (np + 1 < n) {
                        dp[np][sp] = Math.min(go(np + 1, sp + len[np] + 1),
                                go(np, 0) + (m - sp + 1) * (m - sp + 1));
                    } else {
                        dp[np][sp] = 0;
                    }
                } else if (len[np] + sp <= m) {
                    if (np + 1 < n) {
                        dp[np][sp] = Math.min(go(np + 1, 0) + (m - (sp + len[np])) * (m - (sp + len[np])),
                                go(np, 0) + (m - sp + 1) * (m - sp + 1));
                    } else {
                        dp[np][sp] = 0;
                    }
                } else {
                    dp[np][sp] = go(np, 0) + (m - sp + 1) * (m - sp + 1);
                }
            }
        }

        return dp[np][sp];
    }
}