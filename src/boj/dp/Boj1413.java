package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1413 {

    public static void main(String args[]) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine(), " ");
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            long[][] dp = new long[n + 1][n + 1];
            dp[0][0] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i][j] = dp[i - 1][j - 1] + (i - 1) * dp[i - 1][j];
                }
            }

            long s1 = 0L;
            long s2 = 0L;
            long gcd;

            for (int i = 1; i <= n; i++) {
                if (i <= m) s1 += dp[n][i];
                s2 += dp[n][i];
            }

            gcd = gcd(s1, s2);
            writer.write((s1 / gcd) + "/" + (s2 / gcd));
        }
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

}