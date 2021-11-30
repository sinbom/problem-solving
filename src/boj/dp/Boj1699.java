package boj.dp;

import java.io.*;

public class Boj1699 {

    public static int[] dp = new int[100001];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());

            for (int i = 1; i < n + 1; i++) {
                dp[i] = i;
            }

            go(n);

            writer.write(dp[n] + "");
        }
    }

    public static void go(int n) {
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
    }

}