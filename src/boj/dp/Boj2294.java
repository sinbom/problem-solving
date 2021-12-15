package boj.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2294 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int[] coin = new int[n];
            int[] dp = new int[k + 1];

            for (int i = 0; i < n; i++) {
                coin[i] = Integer.parseInt(reader.readLine());
            }

            Arrays.fill(dp, Integer.MAX_VALUE);

            dp[0] = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    if (j >= coin[i] && dp[j - coin[i]] != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
                    }
                }
            }

            int answer = dp[k] == Integer.MAX_VALUE ? -1 : dp[k];
            writer.write(answer + "");
        }
    }

}