package boj.dp;

import java.io.*;

public class Boj3012 {

    public static char[] input;

    public static boolean[][] check;

    public static long[][] dp;

    public static final long MOD = 100000L;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            String str = reader.readLine();
            input = str.toCharArray();
            check = new boolean[n + 1][n + 1];
            dp = new long[n + 1][n + 1];
            long result = solve(0, n - 1);

            if (result >= MOD) {
                writer.write(String.format("%05d\n", result - MOD));
            } else {
                writer.write(result + "\n");
            }
        }
    }

    public static long solve(int s, int e) {
        char[] open = "({[".toCharArray();
        char[] close = ")}]".toCharArray();

        if (check[s][e]) {
            return dp[s][e];
        }

        if (s > e) {
            return 1;
        }

        for (int k = s + 1; k <= e; k += 2) {
            for (int l = 0; l < 3; l++) {
                if (input[s] == open[l] || input[s] == '?') {
                    if (input[k] == close[l] || input[k] == '?') {
                        dp[s][e] += solve(s + 1, k - 1) * solve(k + 1, e);
                        if (dp[s][e] >= MOD) {
                            dp[s][e] = MOD + dp[s][e] % MOD;
                        }

                        check[s][e] = true;
                    }
                }
            }
        }

        return dp[s][e];
    }
}