package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj17069 {

    public static int n;

    public static int[][] map;

    public static long[][][] dp;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            map = new int[n + 1][n + 1];
            dp = new long[n + 1][n + 1][4];
            dp[1][2][1] = 1;

            for (int i = 1; i < n + 1; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j < n + 1; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            for (int i = 1; i < n + 1; i++) {
                for (int j = 3; j < n + 1; j++) {
                    if (map[i][j] == 1) {
                        continue;
                    }

                    dp[i][j][1] = dp[i][j - 1][1] + dp[i][j - 1][2];
                    dp[i][j][3] = dp[i - 1][j][2] + dp[i - 1][j][3];

                    if (map[i][j - 1] == 0 && map[i - 1][j] == 0) {
                        dp[i][j][2] = dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2] + dp[i - 1][j - 1][3];
                    }
                }
            }

            writer.write(dp[n][n][1] + dp[n][n][2] + dp[n][n][3] + "");
        }
    }
}




