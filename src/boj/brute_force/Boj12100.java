package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj12100 {

    public static int[][] arr;

    public static int n;

    public static boolean[][][][] check = new boolean[10][10][10][10];

    public static int answer = 0;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            go(0);

            writer.write(answer + "");
        }
    }

    public static void go(int count) {
        if (count == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, arr[i][j]);
                }
            }
            return;
        }

        int[][] copy = copy();

        for (int i = 0; i < 4; i++) {
            move(i);
            go(count + 1);

            for (int j = 0; j < n; j++) {
                System.arraycopy(copy[j], 0, arr[j], 0, n);
            }
        }

    }

    public static int[][] copy() {
        int[][] copy = new int[n][n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, n);
        }

        return copy;
    }

    public static void move(int dir) {
        if (dir == 0) { // 상
            for (int i = 0; i < n; i++) {
                int index = 0;
                int block = 0;

                for (int j = 0; j < n; j++) {
                    if (arr[j][i] != 0) {
                        if (arr[j][i] == block) {
                            arr[index - 1][i] = block * 2;
                            block = 0;
                            arr[j][i] = 0;
                        } else {
                            block = arr[j][i];
                            arr[j][i] = 0;
                            arr[index][i] = block;
                            index++;
                        }
                    }
                }
            }
        } else if (dir == 1) { // 우
            for (int i = 0; i < n; i++) {
                int index = n - 1;
                int block = 0;

                for (int j = n - 1; j >= 0; j--) {
                    if (arr[i][j] != 0) {
                        if (arr[i][j] == block) {
                            arr[i][index + 1] = block * 2;
                            block = 0;
                            arr[i][j] = 0;
                        } else {
                            block = arr[i][j];
                            arr[i][j] = 0;
                            arr[i][index] = block;
                            index--;
                        }
                    }
                }
            }
        } else if (dir == 2) { // 하
            for (int i = 0; i < n; i++) {
                int index = n - 1;
                int block = 0;

                for (int j = n - 1; j >= 0; j--) {
                    if (arr[j][i] != 0) {
                        if (arr[j][i] == block) {
                            arr[index + 1][i] = block * 2;
                            block = 0;
                            arr[j][i] = 0;
                        } else {
                            block = arr[j][i];
                            arr[j][i] = 0;
                            arr[index][i] = block;
                            index--;
                        }
                    }
                }
            }
        } else { // 좌
            for (int i = 0; i < n; i++) {
                int index = 0;
                int block = 0;

                for (int j = 0; j < n; j++) {
                    if (arr[i][j] != 0) {
                        if (arr[i][j] == block) {
                            arr[i][index - 1] = block * 2;
                            block = 0;
                            arr[i][j] = 0;
                        } else {
                            block = arr[i][j];
                            arr[i][j] = 0;
                            arr[i][index] = block;
                            index++;
                        }
                    }
                }
            }
        }
    }

}