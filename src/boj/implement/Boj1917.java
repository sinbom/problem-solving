package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1917 {

    public static int[][] dx = {
            {1, 2, 1, 1, 1},
            {1, 2, 1, 1, 1},
            {1, 2, 1, 1, 1},
            {1, 2, 1, 1, 1},
            {1, 1, 2, 1, 1},
            {1, 1, 2, 1, 1},
            {1, 1, 1, 0, 0},
            {2, 1, 2, 1, 0},
            {1, 2, 1, 1, 0},
            {0, 1, 2, 1, 1},
            {1, 1, 1, 2, 2}
    };

    public static int[][] dy = {
            {0, 0, 1, 2, 3},
            {-1, -1, 0, 1, 2},
            {-2, -2, -1, 0, 1},
            {-3, -3, -2, -1, 0},
            {-1, 0, 0, 1, 2},
            {-2, -1, -1, 0, 1},
            {-2, -1, 0, 1, 2},
            {-2, -1, -1, 0, 1},
            {-2, -2, -1, 0, 1},
            {1, 1, 1, 2, 3},
            {-1, 0, 1, 1, 2}
    };

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int t = 0; t < 3; t++) {
                int[][] arr = new int[6][6];
                boolean match = false;

                for (int i = 0; i < arr.length; i++) {
                    StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                    for (int j = 0; j < arr.length; j++) {
                        arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    }
                }

                for (int mirror = 0; mirror < 2 && !match; mirror++) {
                    for (int rotate = 0; rotate < 4 && !match; rotate++) {
                        for (int i = 0; i < arr.length && !match; i++) {
                            for (int j = 0; j < arr.length && !match; j++) {
                                if (arr[i][j] == 1) {
                                    match = check(arr, i, j);
                                }
                            }
                        }
                        rotate(arr);
                    }
                    mirror(arr);
                }

                writer.write(match ? "yes\n" : "no\n");
            }
        }
    }

    public static void mirror(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length / 2; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[i][arr.length - 1 - j];
                arr[i][arr.length - 1 - j] = temp;
            }
        }
    }

    public static void rotate(int[][] arr) {
        int[][] temp = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                temp[j][arr.length - 1 - i] = arr[i][j];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(temp[i], 0, arr[i], 0, arr.length);
        }
    }

    public static boolean check(int[][] arr, int x, int y) {
        for (int i = 0; i < dx.length; i++) {
            boolean result = true;

            for (int j = 0; j < dx[i].length; j++) {
                int nx = x + dx[i][j];
                int ny = y + dy[i][j];

                if (nx < 0 || ny < 0 || nx >= arr.length || ny >= arr.length || arr[nx][ny] == 0) {
                    result = false;
                    break;
                }
            }

            if (result) {
                return true;
            }
        }

        return false;
    }

}