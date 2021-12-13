package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11060 {

    public static int n;

    public static int[] arr;

    public static int[] dp;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n];
            dp = new int[n];

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
                dp[i] = Integer.MAX_VALUE;
            }

            dp[0] = 0;

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] == Integer.MAX_VALUE) {
                        writer.write("-1");
                        return;
                    }
                    if (j + arr[j] >= i) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }

            int answer = dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1];
            writer.write(answer + "");
        }
    }


}