package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1912 {

    public static int[] dp = new int[1000001];

    public static int[] numbers = new int[1000001];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i < n + 1; i++) {
                numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            writer.write(go(n) + "");
        }
    }

    public static int go(int n) {
        int max = numbers[1];

        dp[1] = numbers[1];
        for (int i = 2; i <= n; i++) {
            dp[i] = max(dp[i - 1] + numbers[i], numbers[i]);
            max = max(max, dp[i]);
        }

        return max;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

}