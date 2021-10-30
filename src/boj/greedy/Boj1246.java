package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1246 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] p = new int[m];
            int answer = 0;
            int price = 0;

            for (int i = 0; i < m; i++) {
                p[i] = Integer.parseInt(reader.readLine());
            }
            Arrays.sort(p);

            for (int i = 0; i < m; i++) {
                int benefit;
                if (m - i < n) {
                    benefit = p[i] * (m - i);
                } else {
                    benefit = p[i] * n;
                }
                if (answer < benefit) {
                    answer = benefit;
                    price = p[i];
                }
            }

            writer.write(price + " " + answer);
        }
    }

}
