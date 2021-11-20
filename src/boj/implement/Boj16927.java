package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16927 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int r = Integer.parseInt(s[2]);
            int[][] arr = new int[n][m];
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            rotate(arr, r);

            for (int[] values : arr) {
                for (int anInt : values) {
                    sb.append(anInt).append(' ');
                }
                sb.append('\n');
            }

            System.out.println(sb);
        }
    }

    private static void rotate(int[][] arr, int r) {
        int n = arr.length;
        int m = arr[0].length;
        int size = Math.min(n, m) / 2;
        int[] temp = new int[(n + m) * 2 - 4];

        for (int i = 0; i < size; i++) {
            int nx = n - 2 * i;
            int ny = m - 2 * i;
            int length = 0;

            for (int j = 0; j < ny - 1; j++) {
                temp[length++] = arr[i][i + j];
            }

            for (int j = 0; j < nx - 1; j++) {
                temp[length++] = arr[j + i][m - 1 - i];
            }

            for (int j = 0; j < ny - 1; j++) {
                temp[length++] = arr[n - 1 - i][m - 1 - j - i];
            }

            for (int j = 0; j < nx - 1; j++) {
                temp[length++] = arr[n - 1 - i - j][i];
            }

            int index = r % length;

            for (int j = 0; j < ny - 1; j++) {
                arr[i][i + j] = temp[index++ % length];
            }

            for (int j = 0; j < nx - 1; j++) {
                arr[i + j][m - 1 - i] = temp[index++ % length];
            }

            for (int j = 0; j < ny - 1; j++) {
                arr[n - 1 - i][m - 1 - i - j] = temp[index++ % length];
            }

            for (int j = 0; j < nx - 1; j++) {
                arr[n - 1 - i - j][i] = temp[index++ % length];
            }
        }
    }

}