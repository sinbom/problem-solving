package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj17521 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            long w = Long.parseLong(stringTokenizer.nextToken());
            int[] s = new int[n];
            long count = 0;

            for (int i = 0; i < n; i++) {
                s[i] = Integer.parseInt(reader.readLine());
            }

            for (int i = 0; i < n; i++) {
                if (i + 1 < n && s[i] < s[i + 1]) {
                    long buyCount = w / s[i];
                    count += buyCount;
                    w -= buyCount * s[i];
                } else if (i + 1 == n || s[i] > s[i + 1]) {
                    w += count * s[i];
                    count = 0;
                }
            }

            writer.write(w + "");
        }

    }
}
