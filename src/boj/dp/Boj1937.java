package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

    public class Boj1937 {

        public static int[] rangeX = {-1, 0, 1, 0};

        public static int[] rangeY = {0, 1, 0, -1};

        public static int n;

        public static int[][] map;

        public static int[][] dp;

        public static void main(String[] args) throws NumberFormatException, IOException {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
                StringTokenizer st;
                n = Integer.parseInt(reader.readLine());
                map = new int[n][n];
                dp = new int[n][n];

                for (int i = 0; i < n; i++) {
                    st = new StringTokenizer(reader.readLine());
                    for (int j = 0; j < n; j++) {
                        map[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                int ans = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        ans = Math.max(ans, dfs(i, j));
                    }
                }

                writer.write(ans + "\n");
            }

        }

        public static int dfs(int x, int y) {
            if (dp[x][y] != 0) {
                return dp[x][y];
            }

            dp[x][y] = 1;

            int dx, dy;

            for (int i = 0; i < 4; i++) {
                dx = x + rangeX[i];
                dy = y + rangeY[i];

                if (dx < 0 || dy < 0 || dx >= n || dy >= n) {
                    continue;
                }

                if (map[x][y] < map[dx][dy]) {
                    dp[x][y] = Math.max(dp[x][y], dfs(dx, dy) + 1);
                    dfs(dx, dy);
                }
            }

            return dp[x][y];
        }

    }