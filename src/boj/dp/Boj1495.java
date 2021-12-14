package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1495 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] arr = new int[n + 1];
            boolean[][] dp = new boolean[n + 1][m + 1];

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            dp[0][s] = true;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    if (!dp[i - 1][j]) {
                        continue;
                    }

                    if (j + arr[i] <= m) {
                        dp[i][j + arr[i]] = true;
                    }
                    if (j - arr[i] >= 0) {
                        dp[i][j - arr[i]] = true;
                    }
                }
            }

            for (int i = m; i > -1; i--) {
                if (dp[n][i]) {
                    writer.write(i + "");
                    return;
                }
            }

            writer.write("-1");
        }
    }


}