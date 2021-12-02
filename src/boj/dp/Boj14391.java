package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14391 {

    public static int[][] arr;

    public static boolean[][] check;

    public static int n;

    public static int m;

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n][m];
            check = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    arr[i][j] = s.charAt(j) - '0';
                }
            }

            go(0, 0);
            writer.write(answer + "");
        }
    }

    public static void check() {
        int sum = 0;
        int temp;

        for (int i = 0; i < n; i++) {
            temp = 0;
            for (int j = 0; j < m; j++) {
                if (check[i][j]) {
                    temp = temp * 10 + arr[i][j];
                } else {
                    sum += temp;
                    temp = 0;
                }
            }
            sum += temp;
        }

        for (int j = 0; j < m; j++) {
            temp = 0;
            for (int i = 0; i < n; i++) {
                if (!check[i][j]) {
                    temp = temp * 10 + arr[i][j];
                } else {
                    sum += temp;
                    temp = 0;
                }
            }
            sum += temp;
        }

        answer = Math.max(answer, sum);
    }

    public static void go(int x, int y) {
        if (x == n) {
            check();
            return;
        }

        int nx = x;
        int ny = y + 1;

        if (ny == m) {
            nx = x + 1;
            ny = 0;
        }

        check[x][y] = true;
        go(nx, ny);
        check[x][y] = false;
        go(nx, ny);
    }

}