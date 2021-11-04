package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1026 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] a = new int[n];
            int[] b = new int[n];

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            Arrays.sort(a);
            Arrays.sort(b);

            int answer = 0;

            for (int i = 0; i < n; i++) {
                answer += a[i] * b[n - (i + 1)];
            }

            writer.write(answer + "");
        }
    }

}
