package boj.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1695 {

    public static int[][] dp;

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            arr = new int[n];
            dp = new int[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            writer.write(go(0, n - 1) + "");
        }
    }

    public static int go(int start, int end) {
        if (dp[start][end] != -1)
            return dp[start][end];
        if (start == end) {
            return 0;
        } else if (start + 1 == end) {
            if (arr[start] == arr[end]) {
                return 0;
            }

            return 1;
        }
        if (arr[start] == arr[end]) {
            return dp[start][end] = go(start + 1, end - 1);
        } else {
            return dp[start][end] = Math.min(go(start, end - 1), go(start + 1, end)) + 1;
        }
    }

}
