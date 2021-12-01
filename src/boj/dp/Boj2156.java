package boj.dp;

import java.io.*;

public class Boj2156 {

    public static int[] dp = new int[10001];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] arr = new int[10001];

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(reader.readLine());
            }

            dp[1] = arr[1];
            dp[2] = arr[1] + arr[2];

            for (int i = 3; i <= n; i++) {
                dp[i] = Math.max(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i]);
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }

            writer.write(dp[n] + "");
        }
    }

}