package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj16971 {

    public static int[][] a;

    public static int n;

    public static int m;

    public static int max;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] input = reader.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);
            a = new int[n][m];
            max = 0;

            for (int i = 0; i < n; i++) {
                input = reader.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    a[i][j] = Integer.parseInt(input[j]);
                }
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < m - 1; j++) {
                    max += (a[i][j] + a[i + 1][j] + a[i + 1][j + 1] + a[i][j + 1]);
                }
            }

            if (n > 2) {
                int min_row = -1;
                int sum_row = Integer.MAX_VALUE;

                for (int i = 1; i < n - 1; i++) {
                    int sum = 0;
                    for (int j = 0; j < m; j++) {
                        sum += a[i][j];
                    }
                    sum = 4 * sum - 2 * (a[i][0] + a[i][m - 1]);

                    if (sum < sum_row) {
                        sum_row = sum;
                        min_row = i;
                    }
                }

                rotate(min_row, 0, true);
                rotate(min_row, n - 1, true);

            }

            if (m > 2) {
                int min_col = -1;
                int sum_col = Integer.MAX_VALUE;

                for (int j = 1; j < m - 1; j++) {
                    int sum = 0;
                    for (int i = 0; i < n; i++) {
                        sum += a[i][j];
                    }
                    sum = 4 * sum - 2 * (a[0][j] + a[n - 1][j]);

                    if (sum < sum_col) {
                        sum_col = sum;
                        min_col = j;
                    }
                }

                rotate(min_col, 0, false);
                rotate(min_col, m - 1, false);
            }

            writer.write(max + "");
        }
    }

    public static void rotate(int x, int y, boolean flag) {
        int[][] a = new int[n][m];

        if (flag) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == x) {
                        a[y][j] = a[i][j];
                    } else if (i == y) {
                        a[x][j] = a[i][j];
                    } else {
                        a[i][j] = a[i][j];
                    }
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (j == x) {
                        a[i][y] = a[i][j];
                    } else if (j == y) {
                        a[i][x] = a[i][j];
                    } else {
                        a[i][j] = a[i][j];
                    }
                }
            }
        }

        int sum = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                sum += (a[i][j] + a[i + 1][j] + a[i + 1][j + 1] + a[i][j + 1]);
            }
        }

        max = Math.max(sum, max);
    }
}