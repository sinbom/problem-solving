package boj.dp;

import java.io.*;

public class Boj15989 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[][] dp = new int[10001][4];
            int t = Integer.parseInt(reader.readLine());

            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i][j] = 1;
                }
            }

            for (int i = 4; i <= 10000; i++) {
                dp[i][1] = dp[i - 1][1];
                dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
                dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
            }

            while (t-- > 0) {
                int n = Integer.parseInt(reader.readLine());
                int answer = dp[n][1] + dp[n][2] + dp[n][3];
                writer.write(answer + "\n");
            }
        }
    }


}