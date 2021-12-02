package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11055 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] dp = new int[1001];
            int[] numbers = new int[1001];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i <= n; i++) {
                numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
                dp[i] = numbers[i];
            }

            int answer = dp[1];

            for (int i = 2; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    if (numbers[i] > numbers[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + numbers[i]);
                    }
                }
                answer = Math.max(answer, dp[i]);
            }

            writer.write(answer + "");
        }
    }

}