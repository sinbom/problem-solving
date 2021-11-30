package boj.dp;

import java.io.*;

public class Boj15988 {

    public static int[] dp = new int[1000001];

    public static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;

            for (int i = 4; i <= 1000000; i++) {
                dp[i] += dp[i - 3];
                dp[i] %= MOD;
                dp[i] += dp[i - 2];
                dp[i] %= MOD;
                dp[i] += dp[i - 1];
                dp[i] %= MOD;
            }

            int t = Integer.parseInt(reader.readLine());

            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(reader.readLine());
                writer.write(dp[n] + "\n");
            }
        }
    }

}