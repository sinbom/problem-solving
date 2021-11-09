package boj.greedy;

import java.io.*;
import java.util.Arrays;

public class Boj1758 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] tips = new int[n];
            long answer = 0;

            for (int i = 0; i < n; i++) {
                tips[i] = Integer.parseInt(reader.readLine());
            }

            Arrays.sort(tips);

            for (int i = n - 1, j = 0; i > -1; i--, j++) {
                int tip = tips[i] - j;
                if (tip < 1) {
                    break;
                }
                answer += tip;
            }

            writer.write(answer + "");
        }
    }

}
