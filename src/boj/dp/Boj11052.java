package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11052 {

    public static int[] dp = new int[1001];

    public static int[] prices = new int[1001];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i < n + 1; i++) {
                prices[i] = Integer.parseInt(stringTokenizer.nextToken());
                dp[i] = prices[1] * i;
            }

            go(n);

            writer.write(dp[n] + "");
        }
    }

    public static void go(int n) {
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + prices[j]);
            }
        }
    }

}