package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14889 {

    public static int[][] arr;

    public static boolean[] check;

    public static int n;

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            arr = new int[n][n];
            check = new boolean[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            go(0, 0, 0);

            writer.write(answer + "");
        }
    }

    public static void go(int index, int t1, int t2) {
        if (index == n) {
            int result = calculate();
            answer = Math.min(answer, result);
            return;
        }

        if (t1 < n / 2) {
            check[index] = true;
            go(index + 1, t1 + 1, t2);
        }
        if (t2 < n / 2) {
            check[index] = false;
            go(index + 1, t1, t2 + 1);
        }
    }

    public static int calculate() {
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < check.length - 1; i++) {
            for (int j = i + 1; j < check.length; j++) {
                if (check[i] != check[j]) {
                    continue;
                }

                int value = arr[i][j] + arr[j][i];

                if (check[i]) {
                    sum1 += value;
                } else {
                    sum2 += value;
                }
            }
        }

        return Math.abs(sum1 - sum2);
    }

}