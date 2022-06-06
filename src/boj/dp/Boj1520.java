package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1520 {

    public static int n;

    public static int m;

    public static int[][] arr;

    public static int[][] dp;

    public static int[] rangeX = {-1, 0, 1, 0};

    public static int[] rangeY = {0, 1, 0, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            m = Integer.parseInt(stringTokenizer.nextToken());
            n = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[m + 1][n + 1];
            dp = new int[m + 1][n + 1];

            for (int i = 1; i <= m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());

                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }


            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = -1;
                }
            }

            writer.write(dfs(1, 1) + "\n");
        }
    }

    public static int dfs(int x, int y) {
        if (x == m && y == n) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int dx = x + rangeX[i];
            int dy = y + rangeY[i];

            if (dx < 1 || dy < 1 || dx > m || dy > n) {
                continue;
            }

            if (arr[x][y] > arr[dx][dy]) {
                dp[x][y] += dfs(dx, dy);
            }
        }

        return dp[x][y];
    }

}