package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2616 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] train = new int[n + 1];
            int[] sum = new int[n + 1];
            int[][] dp = new int[4][n + 1];

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= n; i++) {
                train[i] = Integer.parseInt(stringTokenizer.nextToken());
                sum[i] = sum[i - 1] + train[i];
            }

            int max = Integer.parseInt(reader.readLine());

            for (int i = 1; i < 4; i++) {
                for (int j = i * max; j <= n; j++) {
                    dp[i][j] = Math.max(
                            dp[i][j - 1],
                            dp[i - 1][j - max] + (sum[j] - sum[j - max])
                    );
                }
            }

            writer.write(dp[3][n] + "");
        }
    }

}