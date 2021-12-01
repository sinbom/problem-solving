package boj.dp;

import java.io.*;

public class Boj11057 {

    public static int[][] dp = new int[1001][10];

    public static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());

            for (int i = 0; i <= 9; i++) {
                dp[1][i] = 1;
            }

            for (int i = 2; i <= n; i++) {
                for (int j = 0; j <= 9; j++) {
                    for (int l = 0; l <= j; l++) {
                        dp[i][j] += dp[i - 1][j - l];
                        dp[i][j] %= MOD;
                    }
                }
            }

            int answer = 0;

            for (int i = 0; i <= 9; i++) {
                answer += dp[n][i];
                answer %= MOD;
            }

            writer.write(answer + "");
        }
    }

}