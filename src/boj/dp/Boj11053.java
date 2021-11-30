package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11053 {

    public static int[] dp = new int[1001];

    public static int[] numbers = new int[1001];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i < n + 1; i++) {
                numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            int answer = go(n);

            writer.write(answer + "");
        }
    }

    public static int go(int n) {
        int result = 1;

        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (numbers[i] > numbers[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }

}