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
                dp[1][i] = 1;
            }

            for (int i = 2; i <= k; i++) {
                for (int j = 0; j <= n; j++) {
                    for (int l = 0; l <= j; l++) {
                        dp[i][j] += dp[i - 1][j - l];
                        dp[i][j] %= MOD;
                    }
                }
            }

            writer.write(dp[k][n] + "");
        }
    }

}