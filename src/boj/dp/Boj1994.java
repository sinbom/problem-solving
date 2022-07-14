package boj.dp;

import java.io.*;
import java.util.Arrays;

public class Boj1994 {

    public static int n;

    public static int[] arr = new int[2000 + 1];

    public static int[][] dp = new int[2000 + 1][2000 + 1];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            n = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n; i++) {
                arr[i + 1] = Integer.parseInt(reader.readLine());
            }

            Arrays.sort(arr, 1, n + 1);

            int max = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    max = Math.max(max, dp(i, j));
                }
            }

            writer.write(max + "");
        }
    }

    public static int dp(int i, int j) {
        int result = dp[i][j];

        if (result != 0) {
            return result;
        }

        int index = binarySearch(arr[j] + arr[j] - arr[i], j + 1);

        if (index == -1) {
            return dp[i][j] = 2;
        } else {
            return dp[i][j] = dp(j, index) + 1;
        }
    }

    public static int binarySearch(int target, int start) {
        int left = start;
        int right = n;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
