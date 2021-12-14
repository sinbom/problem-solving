package boj.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj12869 {

    public static int[][] patterns = {
            {9, 3, 1},
            {9, 1, 3},
            {1, 9, 3},
            {3, 9, 1},
            {1, 3, 9},
            {3, 1, 9}
    };

    public static int[][][] dp = new int[61][61][61];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] arr = new int[3];
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    Arrays.fill(dp[i][j], Integer.MAX_VALUE);
                }
            }

            dp(arr[0], arr[1], arr[2]);
            writer.write(dp[arr[0]][arr[1]][arr[2]] + "");
        }
    }

    public static int dp(int l, int m, int r) {
        if (l == 0 && m == 0 && r == 0) {
            return 0;
        }

        if (dp[l][m][r] != Integer.MAX_VALUE) {
            return dp[l][m][r];
        }

        for (int[] pattern : patterns) {
            int nl = Math.max(l - pattern[0], 0);
            int nm = Math.max(m - pattern[1], 0);
            int nr = Math.max(r - pattern[2], 0);
            dp[l][m][r] = Math.min(dp[l][m][r], dp(nl, nm, nr) + 1);
        }

        return dp[l][m][r];
    }


}