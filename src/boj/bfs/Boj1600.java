package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1600 {

    public static int k;

    public static int n;

    public static int m;

    public static int[][] map;

    public static boolean[][][] check;

    public static int[] hdx = {-2, -2, -1, -1, 1, 1, 2, 2};

    public static int[] hdy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            k = Integer.parseInt(reader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            m = Integer.parseInt(stringTokenizer.nextToken());
            n = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][m];
            check = new boolean[n][m][k + 1];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            writer.write(bfs() + "");
        }
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, k, 0});
        check[0][0][k] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cx = poll[0];
            int cy = poll[1];
            int ck = poll[2];
            int cc = poll[3];

            if (cx == n - 1 && cy == m - 1) {
                return cc;
            }

            if (ck > 0) {
                for (int i = 0; i < hdx.length; i++) {
                    int nx = cx + hdx[i];
                    int ny = cy + hdy[i];
                    int nk = ck - 1;

                    if (isOutSide(nx, ny) || isInvalid(nx, ny, nk)) {
                        continue;
                    }

                    check[nx][ny][nk] = true;
                    queue.add(new int[]{nx, ny, nk, cc + 1});
                }
            }

            for (int i = 0; i < dy.length; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int nk = ck;

                if (isOutSide(nx, ny) || isInvalid(nx, ny, nk)) {
                    continue;
                }

                check[nx][ny][nk] = true;
                queue.add(new int[]{nx, ny, nk, cc + 1});
            }
        }

        return -1;
    }

    public static boolean isOutSide(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }

    public static boolean isInvalid(int x, int y, int k) {
        return map[x][y] != 0 || check[x][y][k];
    }

}