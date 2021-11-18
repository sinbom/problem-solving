package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16935 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int r = Integer.parseInt(s[2]);
            int[][] arr = new int[n][m];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            while (r-- > 0) {
                int op = Integer.parseInt(stringTokenizer.nextToken());

                if (op == 1) { // 상하 반전
                    for (int i = 0; i < n / 2; i++) {
                        int[] temp = arr[i];
                        arr[i] = arr[n - 1 - i];
                        arr[n - 1 - i] = temp;
                    }
                } else if (op == 2) { // 좌우 반전
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m / 2; j++) {
                            int temp = arr[i][j];
                            arr[i][j] = arr[i][m - 1 - j];
                            arr[i][m - 1 - j] = temp;
                        }
                    }
                } else if (op == 3) { // 오른쪽 90도 회전
                    int[][] temp = new int[m][n];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            temp[j][n - 1 - i] = arr[i][j];
                        }
                    }
                    arr = temp;
                    n = arr.length;
                    m = arr[0].length;
                } else if (op == 4) { // 왼쪽으로 90도 회전
                    int[][] temp = new int[m][n];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            temp[m - 1 - j][i] = arr[i][j];
                        }
                    }
                    arr = temp;
                    n = arr.length;
                    m = arr[0].length;
                } else if (op == 5) { // 4등분으로 나눠 각 등분의 위치를 시계방향으로 이동
                    int[][] temp = new int[n / 2][m / 2];

                    for (int i = 0; i < n / 2; i++) {
                        for (int j = 0; j < m / 2; j++) {
                            temp[i][j] = arr[i][j];
                            arr[i][j] = arr[i + n / 2][j];
                            arr[i + n / 2][j] = arr[i + n / 2][j + m / 2];
                            arr[i + n / 2][j + m / 2] = arr[i][j + m / 2];
                            arr[i][j + m / 2] = temp[i][j];
                        }
                    }
                } else { // 4등분으로 나눠 각 등분의 위치를 반시계방향으로 이동
                    int[][] temp = new int[n / 2][m / 2];

                    for (int i = 0; i < n / 2; i++) {
                        for (int j = 0; j < m / 2; j++) {
                            temp[i][j] = arr[i][j];
                            arr[i][j] = arr[i][j + m / 2];
                            arr[i][j + m / 2] = arr[i + n / 2][j + m / 2];
                            arr[i + n / 2][j + m / 2] = arr[i + n / 2][j];
                            arr[i + n / 2][j] = temp[i][j];
                        }
                    }
                }

            }

            for (int[] ints : arr) {
                for (int anInt : ints) {
                    writer.write(anInt + " ");
                }
                writer.newLine();
            }

        }
    }

}