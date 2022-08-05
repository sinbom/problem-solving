package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj1994 {

    private static int n;

    private static int[] arr = new int[2000 + 1];

    private static int[][] dp = new int[2000 + 1][2000 + 1];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n; i++) {
                arr[i + 1] = Integer.parseInt(reader.readLine());
            }

            Arrays.sort(arr, 1, n + 1);

            int answer = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    answer = Math.max(answer, dp(i, j));
                }
            }

            writer.append(String.valueOf(answer));
        }
    }

    private static int dp(int i, int j) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            return 1;
        }

        int result = dp[i][j];

        if (result != 0) {
            return result;
        }

        int target = 2 * arr[j] - arr[i];
        int index = binarySearch(target, j + 1);

        if (index == -1) {
            return dp[i][j] = 2;
        }

        return dp[i][j] = dp(j, index) + 1;
    }

    private static int binarySearch(int target, int start) {
        int left = start;
        int right = n;
        int mid = (left + right) / 2;

        while (left < right) {
            mid = (left + right) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (left <= n && arr[left] == target) {
            return left;
        }

        if (arr[mid] == target) {
            return mid;
        }

        return -1;
    }

}