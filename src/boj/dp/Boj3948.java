package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj3948 {

    public static long[] d;

    public static long[][] dp;

    public static int n;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());
            StringBuilder stringBuilder = new StringBuilder();

            dp = new long[21][21];

            while (t-- > 0) {
                n = Integer.parseInt(reader.readLine());
                d = new long[n + 1];

                if (n == 1) {
                    stringBuilder.append(1);
                } else {
                    stringBuilder.append(2L * go(n));
                }

                stringBuilder.append("\n");
            }

            writer.write(stringBuilder.toString());
        }
    }

    public static long go(int n) {
        if (n <= 2) {
            return d[n] = 1;
        }
        if (d[n] != 0) {
            return d[n];
        }
        long cnt = 0;
        for (int i = 1; i <= n; i += 2) {
            cnt += go(i - 1) * go(n - i) * dp(n - 1, i - 1);
        }

        return d[n] = cnt;
    }

    public static long dp(int n, int r) {
        if (n == 0 || r == 0) {
            return dp[n][r] = 1;
        }

        if (n == r) {
            return dp[n][r] = 1;
        }

        if (n < r) {
            return dp[n][r] = 0;
        }

        if (dp[n][r] != 0) {
            return dp[n][r];
        }

        return dp[n][r] = dp(n - 1, r - 1) + dp(n - 1, r);
    }
}

