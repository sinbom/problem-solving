package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1932 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[][] dp = new int[501][];

            for (int i = 1; i <= n; i++) {
                dp[i] = new int[i + 2];
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= i; j++) {
                    dp[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + dp[i][j];
                }
            }

            int answer = dp[n][1];

            for (int i = 2; i <= n; i++) {
                answer = Math.max(answer, dp[n][i]);
            }

            writer.write(answer + "");
        }
    }

}