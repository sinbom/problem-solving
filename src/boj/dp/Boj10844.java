package boj.dp;

import java.io.*;

public class Boj10844 {

    public static int[][] dp = new int[101][10];

    public static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int answer = 0;

            go(n);
            for (int i = 0; i <= 9; i++) {
                answer += dp[n][i];
                answer %= MOD;
            }

            writer.write(answer + "");
        }
    }

    public static void go(int n) {
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][1];
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
            }
            dp[i][9] = dp[i - 1][8];
        }
    }

}