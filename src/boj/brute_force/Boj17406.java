package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj17406 {

    public static int[][] board;

    public static int[][] rotation;

    public static int min = Integer.MAX_VALUE;

    public static int n, m;

    public static boolean[] visited;

    public static int[] result;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());

            board = new int[n][m];
            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            rotation = new int[k][3];
            for (int i = 0; i < k; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                rotation[i][0] = Integer.parseInt(stringTokenizer.nextToken());
                rotation[i][1] = Integer.parseInt(stringTokenizer.nextToken());
                rotation[i][2] = Integer.parseInt(stringTokenizer.nextToken());
            }

            visited = new boolean[k];
            result = new int[k];
            go(0, k);

            writer.write(min + "");
        }
    }

    public static void go(int idx, int k) {
        if (idx == k) {
            int[][] copy = new int[n][m];

            for (int i = 0; i < n; i++) {
                copy[i] = board[i].clone();
            }
            findMin(copy);

            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[idx] = i;
                go(idx + 1, k);
                visited[i] = false;
            }
        }
    }

    public static void findMin(int[][] copy) {
        for (int i = 0; i < result.length; i++) {
            int lx = rotation[result[i]][0] - rotation[result[i]][2] - 1;
            int ly = rotation[result[i]][1] - rotation[result[i]][2] - 1;
            int rx = rotation[result[i]][0] + rotation[result[i]][2] - 1;
            int ry = rotation[result[i]][1] + rotation[result[i]][2] - 1;
            rotate(lx, ly, rx, ry, copy);
        }
        rowcal(copy);
    }

    public static void rowcal(int[][] copy) {
        for (int i = 0; i < copy.length; i++) {
            int sum = 0;

            for (int j = 0; j < copy[i].length; j++) {
                sum += copy[i][j];
            }

            min = Math.min(min, sum);
        }
    }

    public static void rotate(int lx, int ly, int rx, int ry, int[][] copy) {
        if (lx == rx && ly == ry) {
            return;
        }

        int[] temp = new int[3];

        temp[0] = copy[lx][ry];
        temp[1] = copy[rx][ry];
        temp[2] = copy[rx][ly];

        for (int i = ry; i > ly; i--) {
            copy[lx][i] = copy[lx][i - 1];
        }

        for (int i = rx; i > lx; i--) {
            if (i == lx + 1) {
                copy[i][ry] = temp[0];
            } else {
                copy[i][ry] = copy[i - 1][ry];
            }
        }

        for (int i = ly; i < ry; i++) {
            if (i == ry - 1) {
                copy[rx][i] = temp[1];
            } else {
                copy[rx][i] = copy[rx][i + 1];
            }
        }

        for (int i = lx; i < rx; i++) {
            if (i == rx - 1) {
                copy[i][ly] = temp[2];
            } else {
                copy[i][ly] = copy[i + 1][ly];
            }
        }

        rotate(lx + 1, ly + 1, rx - 1, ry - 1, copy);
    }
}

