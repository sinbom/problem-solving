package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj15992 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(reader.readLine());
            long[][] dp = new long[1001][1001];

            dp[1][1] = 1;
            dp[2][1] = 1;
            dp[2][2] = 1;
            dp[3][1] = 1;
            dp[3][2] = 2;
            dp[3][3] = 1;

            for (int i = 4; i <= 1000; i++) {
                for (int j = 2; j < i; j++) {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 2][j - 1] + dp[i - 3][j - 1]) % 1_000_000_009;
                }
                dp[i][i] = 1;
            }

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int first = Integer.parseInt(stringTokenizer.nextToken());
                int second = Integer.parseInt(stringTokenizer.nextToken());
                sb
                        .append(dp[first][second])
                        .append("\n");
            }

            writer.write(sb.toString());
        }
    }

}
