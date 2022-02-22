package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11049 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[][] a = new int[n][2];
            int[][] dp = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                a[i][0] = Integer.parseInt(stringTokenizer.nextToken());
                a[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n - i; j++) {
                    dp[j][j + i] = Integer.MAX_VALUE;
                    for (int k = 0; k < i; k++) {
                        int cost = dp[j][j + k] + dp[j + k + 1][j + i] + a[j][0] * a[j + k][1] * a[j + i][1];
                        dp[j][j + i] = Math.min(dp[j][j + i], cost);
                    }
                }
            }

            writer.write(dp[0][n - 1] + "");
        }
    }
}
