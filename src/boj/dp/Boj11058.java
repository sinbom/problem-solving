package boj.dp;

import java.io.*;

public class Boj11058 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            long[] dp = new long[n + 1];

            dp[1] = 1;

            for (int i = 2; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    dp[i] = Math.max(dp[i], dp[j] + (i - j));
                    if (j < i - 2) {
                        dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
                    }
                }
            }

            writer.write(dp[n] + "");
        }
    }

}