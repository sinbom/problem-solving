package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2098 {

    private static int n;

    private static int[][] map;

    private static int[][] dp;

    private static final int INF = 11000000;

    public static void main(String args[]) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            map = new int[n][n];
            dp = new int[n][(1 << n) - 1];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], INF);
            }

            writer.write(dfs(0, 1) + "");
        }
    }

    private static int dfs(int city, int bitmask) {
        if (bitmask == (1 << n) - 1) {
            if (map[city][0] == 0) {
                return INF;
            }

            return map[city][0];
        }

        if (dp[city][bitmask] != INF) {
            return dp[city][bitmask];
        }

        for (int i = 0; i < n; i++) {
            if ((bitmask & (1 << i)) != 0 || map[city][i] == 0) {
                continue;
            }

            dp[city][bitmask] = Math.min(
                dp[city][bitmask],
                dfs(i, bitmask | (1 << i)) + map[city][i]
            );
        }

        return dp[city][bitmask];
    }

}
