package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj13398 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[][] dp = new int[100001][2];
            int[] numbers = new int[100001];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i <= n; i++) {
                numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            dp[1][0] = numbers[1];

            int answer = dp[1][0];

            for (int i = 2; i <= n; i++) {
                dp[i][0] = max(dp[i - 1][0] + numbers[i], numbers[i]);
                dp[i][1] = max(dp[i - 1][0], dp[i - 1][1] + numbers[i]);
                answer = max(answer, max(dp[i][0], dp[i][1]));
            }

            writer.write(answer + "");
        }
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

}