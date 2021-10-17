package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1434 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] a = new int[n];
            int[] b = new int[m];

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            int i = 0;
            int j = 0;
            int answer = 0;

            while (i < a.length && j < b.length) {
                if (a[i] < b[j]) {
                    answer += a[i];
                    i++;
                } else {
                    a[i] -= b[j];
                    j++;
                }
            }

            while (i < a.length) {
                answer += a[i];
                i++;
            }

            writer.write(answer + "");
        }
    }

}
