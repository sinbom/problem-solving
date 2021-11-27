package boj.dp;

import java.io.*;

public class Boj1463 {

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            arr = new int[n + 1];
            writer.write(dp(n) + "");
        }
    }

    public static int dp(int n) {
        if (n == 1) {
            return 0;
        }
        if (arr[n] > 0) {
            return arr[n];
        }

        arr[n] = dp(n - 1) + 1;

        if (n % 3 == 0) {
            int temp = dp(n / 3) + 1;
            arr[n] = arr[n] > temp ? temp : arr[n];
        }
        if (n % 2 == 0) {
            int temp = dp(n / 2) + 1;
            arr[n] = arr[n] > temp ? temp : arr[n];
        }

        return arr[n];
    }

}