package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj11660 {

    static int n, m;
    static int[][] map; // 입력받는 값
    static int[][] dp; // dp [i][j] = (1,1)에서 (i,j) 까지의 합

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n + 1][n + 1];
            dp = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());

                for (int j = 1; j <= n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i][j];
                }
            }

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 1; i <= m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int x1 = Integer.parseInt(stringTokenizer.nextToken());
                int y1 = Integer.parseInt(stringTokenizer.nextToken());
                int x2 = Integer.parseInt(stringTokenizer.nextToken());
                int y2 = Integer.parseInt(stringTokenizer.nextToken());

                stringBuilder
                        .append(dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1])
                        .append("\n");
            }

            writer.write(stringBuilder.toString());
        }
    }
}