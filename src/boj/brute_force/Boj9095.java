package boj.brute_force;

import java.io.*;

public class Boj9095 {

    public static int[] arr = new int[11];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());

            arr[1] = 1;
            arr[2] = 2;
            arr[3] = 4;
            dp(10);

            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(reader.readLine());
                writer.write(arr[n] + "\n");
            }
        }
    }

    public static void dp(int n) {
        for (int i = 4; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
        }
    }

}