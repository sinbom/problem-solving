package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj20044 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] w = new int[2 * n];
            int answer = Integer.MAX_VALUE;

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < w.length; i++) {
                w[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            Arrays.sort(w);

            for (int i = 0; i < n; i++) {
                answer = Math.min(answer, w[i] + w[w.length - 1 - i]);
            }

            writer.write(answer + "");
        }
    }

}
