package boj.dp;

import java.io.*;

public class Boj1309 {

    public static int[][] dp = new int[100001][3];

    public static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());

            dp[1][0] = dp[1][1] = dp[1][2] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i][0] += dp[i - 1][0];
                dp[i][0] %= MOD;
                dp[i][0] += dp[i - 1][1];
                dp[i][0] %= MOD;
                dp[i][0] += dp[i - 1][2];
                dp[i][0] %= MOD;

                dp[i][1] += dp[i - 1][0];
                dp[i][0] %= MOD;
                dp[i][1] += dp[i - 1][2];
                dp[i][0] %= MOD;

                dp[i][2] += dp[i - 1][0];
                dp[i][0] %= MOD;
                dp[i][2] += dp[i - 1][1];
                dp[i][0] %= MOD;
            }

            int answer = dp[n][0];
            answer += dp[n][1];
            answer %= MOD;
            answer += dp[n][2];
            answer %= MOD;

            writer.write(answer + "");
        }
    }

}