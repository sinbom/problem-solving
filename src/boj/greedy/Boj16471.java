package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj16471 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] joo = new int[n];
            int[] sa = new int[n];
            int answer = 0;
            int until = (n + 1) / 2;

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                joo[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                sa[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            Arrays.sort(joo);
            Arrays.sort(sa);

            for (int i = 0; i < until; i++) {
                if (joo[i] < sa[n / 2 + i]) {
                    answer++;
                }
            }

            writer.write(answer >= until ? "YES" : "NO");
        }
    }

}
