package boj.dp;

import java.io.*;

public class Boj11727 {

    public static int[] arr = new int[1001];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            arr[1] = 1;
            arr[2] = 3;
            writer.write(dp(n) + "");
        }
    }

    public static int dp(int n) {
        if (arr[n] > 0) {
            return arr[n];
        }

        return arr[n] = (dp(n - 1) + dp(n - 2) * 2) % 10007;
    }

}