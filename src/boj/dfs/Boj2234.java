package boj.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2234 {

    public static int n;

    public static int m;

    public static int[][] map;

    public static boolean[][] check;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int[] d = {2, 4, 8, 1};

    public static int max = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            m = Integer.parseInt(stringTokenizer.nextToken());
            n = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][m];
            check = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int count = 0;
            int width = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (check[i][j]) {
                        continue;
                    }
                    count++;
                    width = Math.max(width, dfs(i, j, 1));
                }
            }

            writer.write(count + "\n");
            writer.write(width + "\n");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < d.length; k++) {
                        if ((map[i][j] & d[k]) == d[k]) {
                            init();
                            map[i][j] -= d[k];
                            width = Math.max(width, dfs(i, j, 1));
                            map[i][j] += d[k];
                        }
                    }
                }
            }

            writer.write(width + "");
        }
    }

    public static void init() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(check[i], false);
        }
    }

    public static int dfs(int x, int y, int width) {
        check[x][y] = true;

        for (int i = 0; i < d.length; i++) {
            if ((map[x][y] & d[i]) != d[i]) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (check[nx][ny]) {
                    continue;
                }

                check[nx][ny] = true;
                width = dfs(nx, ny, width + 1);
            }
        }

        return width;
    }

}