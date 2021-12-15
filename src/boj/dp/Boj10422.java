package boj.dp;

import java.io.*;

public class Boj10422 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            long[] dp = new long[5001];

            dp[0] = dp[2] = 1;

            for (int i = 3; i <= 5000; i++) {
                for (int j = 2; j <= i; j++) {
                    dp[i] += dp[j - 2] * dp[i - j];
                    dp[i] %= 1000000007;
                }
            }

            int t = Integer.parseInt(reader.readLine());

            while (t-- > 0) {
                int n = Integer.parseInt(reader.readLine());
                writer.write(dp[n] + "\n");
            }
        }
    }

}