package boj.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16929 {

    public static char[][] arr;

    public static boolean[][] check;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static boolean flag = false;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            arr = new char[n][m];
            check = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    arr[i][j] = s.charAt(j);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dfs(i, j, i, j, 1);
                    check[i][j] = true;
                    if (flag) {
                        writer.write("Yes");
                        return;
                    }
                }
            }

            writer.write("No");
        }
    }

    public static void dfs(int x, int y, int sx, int sy, int depth) {
        if (flag) {
            return;
        }

        check[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= arr.length || ny >= arr[0].length || arr[nx][ny] != arr[x][y]) {
                continue;
            }

            if (nx == sx && ny == sy && depth >= 4) {
                flag = true;
                return;
            }

            if (!check[nx][ny]) {
                dfs(nx, ny, sx, sy, depth + 1);
            }
        }
        check[x][y] = false;
    }

}