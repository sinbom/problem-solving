package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2169 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[][] mars = new int[n + 1][m + 1];
            int[][] dp = new int[n + 1][m + 1];
            int[][] temp = new int[2][m + 2];


            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());

                for (int j = 1; j <= m; j++) {
                    mars[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            dp[1][1] = mars[1][1];

            for (int j = 2; j <= m; j++) {
                dp[1][j] = mars[1][j] + dp[1][j - 1];
            }

            for (int i = 2; i <= n; i++) {
                temp[0][0] = dp[i - 1][1];

                for (int j = 1; j <= m; j++) {
                    temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + mars[i][j];
                }

                temp[1][m + 1] = dp[i - 1][m];

                for (int j = m; j >= 1; j--) {
                    temp[1][j] = Math.max(temp[1][j + 1], dp[i - 1][j]) + mars[i][j];
                }

                for (int j = 1; j <= m; j++) {
                    dp[i][j] = Math.max(temp[0][j], temp[1][j]);
                }
            }

            writer.write(dp[n][m] + "");
        }
    }

}

