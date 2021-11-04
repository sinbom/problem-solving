package boj.greedy;

import java.io.*;
import java.util.Arrays;

public class Boj2217 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(reader.readLine());
            }
            Arrays.sort(a);

            int answer = 0;

            for (int i = 0; i < n; i++) {
                answer = Math.max(a[i] * (n - i), answer);
            }

            writer.write(answer + "");
        }
    }

}