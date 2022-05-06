package boj.tree;

import java.io.*;
import java.util.StringTokenizer;

public class Boj17435 {
    private final static int log = 19;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder stringBuilder = new StringBuilder();
            int m = Integer.parseInt(reader.readLine());
            int[][] dp = new int[log + 1][m + 1];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i < m + 1; i++) {
                dp[0][i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int i = 1; i < log + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    dp[i][j] = dp[i - 1][dp[i - 1][j]];
                }
            }

            int q = Integer.parseInt(reader.readLine());

            while (q-- > 0) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int n = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());

                for (int b = 0; b < log; b++) {
                    if ((n & (1 << b)) > 0) {
                        x = dp[b][x];
                    }
                }
                stringBuilder.append(x).append("\n");
            }

            writer.write(stringBuilder.toString() + "\n");
        }
    }
}


