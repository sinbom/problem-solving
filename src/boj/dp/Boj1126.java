package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Boj1126 {

    private static final int MAX_DIFF = 250000;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] a = new int[n + 1];
            int[][] d = new int[55][MAX_DIFF + 1];

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(stringTokenizer.nextToken());
            }


            Arrays.fill(d[0], Integer.MIN_VALUE);
            d[0][0] = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= MAX_DIFF; j++) {
                    d[i][j] = d[i - 1][j];
                    if (j - a[i] >= 0) {
                        d[i][j] = Math.max(d[i][j], d[i - 1][j - a[i]] + a[i]);
                    } else if (a[i] - j <= MAX_DIFF) {
                        d[i][j] = Math.max(d[i][j], d[i - 1][a[i] - j] + j);
                    }
                    if (j + a[i] <= MAX_DIFF) {
                        d[i][j] = Math.max(d[i][j], d[i - 1][j + a[i]]);
                    }
                }
            }

            if (d[n][0] <= 0) {
                writer.write("-1");
            } else {
                writer.write(d[n][0] + "\n");
            }
        }
    }

}
