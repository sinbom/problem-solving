package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj1328 {

    public static long[][][] dp;

    public static int div = 1000000007;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int l = Integer.parseInt(stringTokenizer.nextToken());
            int r = Integer.parseInt(stringTokenizer.nextToken());

            dp = new long[n + 1][n + 1][n + 1];
            dp[1][1][1] = 1;

            for (int i = 2; i < n + 1; i++) {
                dp[i][i][1] = dp[i][1][i] = 1;

                for (int j = 1; j < l + 1; j++) {
                    for (int k = 1; k < r + 1; k++) {
                        dp[i][j][k] = (dp[i - 1][j][k - 1] + dp[i - 1][j - 1][k] + (dp[i - 1][j][k] * (i - 2))) % div;
                    }
                }
            }

            writer.write(dp[n][l][r] + "");
        }
    }

}

