package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj10942 {

    public static int[] dp;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] arr = new int[n + 1];
            boolean[][] dp = new boolean[n + 1][n + 1];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
                dp[i][i] = true;
                if (arr[i - 1] == arr[i]) {
                    dp[i - 1][i] = true;
                }
            }

            for (int i = 2; i < n; i++) {
                for (int j = 1; j + i <= n; j++) {
                    if (arr[j] == arr[j + i] && dp[j + 1][j + i - 1]) {
                        dp[j][j + i] = true;
                    }
                }
            }

            int m = Integer.parseInt(reader.readLine());

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int s = Integer.parseInt(stringTokenizer.nextToken());
                int e = Integer.parseInt(stringTokenizer.nextToken());

                writer.write(dp[s][e] ? "1" : "0");
                writer.newLine();
            }
        }
    }


}