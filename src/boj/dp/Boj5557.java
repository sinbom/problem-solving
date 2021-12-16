package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj5557 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] arr = new int[n + 1];
            long[][] dp = new long[n + 1][21];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            dp[1][arr[1]] = 1;

            for (int i = 2; i < n; i++) {
                for (int j = 0; j <= 20; j++) {
                    if (j + arr[i] <= 20) {
                        dp[i][j + arr[i]] += dp[i - 1][j];
                    }
                    if (j - arr[i] >= 0) {
                        dp[i][j - arr[i]] += dp[i - 1][j];
                    }
                }
            }

            writer.write(dp[n - 1][arr[n]] + "");
        }
    }

}