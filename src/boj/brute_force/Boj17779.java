package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17779 {

    public static int n;

    public static int[][] arr;

    public static int total = 0;

    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    total += arr[i][j];
                }
            }

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    for (int d1 = 1; d1 < n; d1++) {
                        for (int d2 = 1; d2 < n; d2++) {
                            if (x + d1 + d2 >= n) {
                                continue;
                            }
                            if (y - d1 < 0 || y + d2 >= n) {
                                continue;
                            }
                            go(x, y, d1, d2);
                        }
                    }
                }
            }

            writer.write(min + "");
        }
    }

    public static void go(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[n][n];
        int[] peopleSum = new int[5];

        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (border[i][j]) break;
                peopleSum[0] += arr[i][j];
            }
        }

        for (int i = 0; i <= x + d2; i++) {
            for (int j = n - 1; j > y; j--) {
                if (border[i][j]) break;
                peopleSum[1] += arr[i][j];
            }
        }

        for (int i = x + d1; i < n; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (border[i][j]) break;
                peopleSum[2] += arr[i][j];
            }
        }

        for (int i = x + d2 + 1; i < n; i++) {
            for (int j = n - 1; j >= y - d1 + d2; j--) {
                if (border[i][j]) break;
                peopleSum[3] += arr[i][j];
            }
        }

        peopleSum[4] = total;

        for (int i = 0; i < 4; i++) {
            peopleSum[4] -= peopleSum[i];
        }

        Arrays.sort(peopleSum);
        min = Math.min(min, peopleSum[4] - peopleSum[0]);
    }
}



