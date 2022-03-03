package boj.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj12996 {

    public static long[][][][] dp;

    public static final long mod = 1000000007;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            dp = new long[s + 1][a + 1][b + 1][c + 1];

            for (int i = 0; i <= s; i++) {
                for (int j = 0; j <= a; j++) {
                    for (int k = 0; k <= b; k++) {
                        Arrays.fill(dp[i][j][k], -1);
                    }
                }
            }

            writer.write(go(s, a, b, c) + "");
        }
    }

    public static long go(int s, int a, int b, int c) {
        if (s <= 0) {
            if (a == 0 && b == 0 && c == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (a < 0 || b < 0 || c < 0) {
            return 0;
        }

        if (dp[s][a][b][c] != -1) {
            return dp[s][a][b][c];
        }

        long answer = 0;

        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= 1; k++) {
                    if (i + j + k > 0) {
                        answer += go(s - 1, a - i, b - j, c - k);
                    }
                }
            }
        }

        answer %= mod;
        dp[s][a][b][c] = answer;

        return answer;
    }
}