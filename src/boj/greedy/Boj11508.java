package boj.greedy;

import java.io.*;
import java.util.Arrays;

public class Boj11508 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] c = new int[n];
            long answer = 0;

            for (int i = 0; i < n; i++) {
                c[i] = Integer.parseInt(reader.readLine());
            }
            Arrays.sort(c);

            for (int i = n - 1; i > -1; i -= 3) {
                answer += c[i];
                if (i - 1 > -1) {
                    answer += c[i - 1];
                }
            }

            writer.write(answer + "");
        }
    }

}
