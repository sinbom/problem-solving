package boj.greedy;

import java.io.*;

public class Boj2847 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] p = new int[n];

            for (int i = 0; i < n; i++) {
                p[i] = Integer.parseInt(reader.readLine());
            }

            if (n == 1) {
                writer.write("0");
                return;
            }

            int answer = 0;

            for (int i = n - 2; i > -1; i--) {
                if (p[i] >= p[i + 1]) {
                    int diff = Math.abs(p[i] - p[i + 1]) + 1;
                    p[i] -= diff;
                    answer += diff;
                }
            }

            writer.write(answer + "");
        }
    }

}
