package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14002 {

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

            int index = go(n);
            int length = dp[index];
            int[] arr = new int[length];

            for (int i = index, j = length, k = length - 1; i > 0; i--) {
                if (dp[i] == j) {
                    arr[k--] = numbers[i];
                    j--;
                }
            }

            writer.write(length + "\n");
            for (int i = 0; i < length; i++) {
                writer.write(arr[i] + " ");
            }
        }
    }

    public static int go(int n) {
        int max = 1;
        int result = 1;

        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (numbers[i] > numbers[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (max < dp[i]) {
                max = dp[i];
                result = i;
            }
        }

        return result;
    }

}