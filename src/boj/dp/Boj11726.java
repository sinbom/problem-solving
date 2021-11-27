package boj.dp;

import java.io.*;

public class Boj11726 {

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
        if (n < 3) {
            return n;
        }
        if (arr[n] > 0) {
            return arr[n];
        }

        return arr[n] = (dp(n - 1) + dp(n - 2)) % 10007;
    }

}