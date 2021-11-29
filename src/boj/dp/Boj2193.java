package boj.dp;

import java.io.*;

public class Boj2193 {

    public static long[][] dp = new long[91][2];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());

            go(n);
            long answer = dp[n][0] + dp[n][1];

            writer.write(answer + "");
        }
    }

    public static void go(int n) {
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }
    }

}