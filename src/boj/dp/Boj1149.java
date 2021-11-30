package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1149 {

    public static int[][] dp = new int[1001][4];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[][] cost = new int[n + 1][4];

            for (int i = 1; i <= n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= 3; j++) {
                    cost[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            for (int i = 1; i <= n; i++) {
                dp[i][1] = Math.min(dp[i - 1][2], dp[i - 1][3]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][3]) + cost[i][2];
                dp[i][3] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][3];
            }

            int answer = Math.min(Math.min(dp[n][1], dp[n][2]), dp[n][3]);

            writer.write(answer + "");
        }
    }

}