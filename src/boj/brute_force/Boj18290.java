package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj18290 {

    public static int max = Integer.MIN_VALUE;

    public static boolean[][] check;

    public static int[][] arr;

    public static int[][] direction = {
            {0, 0, 1, -1},
            {1, -1, 0, 0}
    };

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            int k = Integer.parseInt(tokenizer.nextToken());

            arr = new int[n][m];
            check = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            go(0, 0, 0, k, 0);
            writer.write(max + "");
        }
    }

    public static void go(int pi, int pj, int sum, int k, int depth) {
        if (depth == k) {
            if (max < sum) {
                max = sum;
            }
        } else {
            for (int i = pi; i < arr.length; i++) {
                for (int j = (i == pi ? pj : 0); j < arr[i].length; j++) {
                    if (check[i][j]) {
                        continue;
                    }
                    boolean ok = true;
                    for (int l = 0; l < direction[0].length; l++) {
                        int nx = i + direction[0][l];
                        int ny = j + direction[1][l];
                        if (nx >= 0 && nx < arr.length && ny >= 0 && ny < arr[i].length && check[nx][ny]) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        check[i][j] = true;
                        go(i, j, sum + arr[i][j], k, depth + 1);
                        check[i][j] = false;
                    }
                }
            }
        }
    }

}