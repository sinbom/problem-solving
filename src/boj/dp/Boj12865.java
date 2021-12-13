package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj12865 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int[][] items = new int[n + 1][2];
            int[][] dp = new int[n + 1][k + 1];

            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                items[i][0] = Integer.parseInt(stringTokenizer.nextToken());
                items[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    if (j >= items[i][0]) {
                        dp[i][j] = Math.max(
                                dp[i - 1][j],
                                dp[i - 1][j - items[i][0]] + items[i][1]
                        );
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            writer.write(dp[n][k] + "");
        }
    }


}