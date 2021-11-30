package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2225 {

    public static int[][] dp = new int[201][201];

    public static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());

            for (int i = 0; i <= n; i++) {
                dp[i][1] = 1;
            }

            for (int i = 0; i <= n; i++) {
                for (int j = 2; j <= k; j++) {
                    for (int l = 0; l <= i; l++) {
                        dp[i][j] += dp[i - l][j - 1];
                        dp[i][j] %= MOD;
                    }
                }
            }

            writer.write(dp[n][k] + "");
        }
    }

}