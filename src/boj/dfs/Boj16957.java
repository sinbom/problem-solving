package boj.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16957 {

    public static int r;

    public static int c;

    public static int[] parent;

    public static int[][] arr;

    public static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            r = Integer.parseInt(stringTokenizer.nextToken());
            c = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[r][c];
            parent = new int[r * c];
            int maxSize = r * c;
            int[] ans = new int[maxSize];

            for (int i = 0; i < r; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < c; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    parent[i * c + j] = i * c + j;
                }
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (parent[i * c + j] == i * c + j) {
                        dfs(i, j);
                    }
                }
            }

            for (int i = 0; i < maxSize; i++) {
                ans[find(i)]++;
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sb
                            .append(ans[i * c + j])
                            .append(" ");
                }
                sb.append("\n");
            }
            writer.write(sb.toString());
        }
    }

    public static void dfs(int x, int y) {
        int minVal = arr[x][y];
        int minX = 0;
        int minY = 0;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (check(nx, ny) && minVal > arr[nx][ny]) {
                minVal = arr[nx][ny];
                minX = nx;
                minY = ny;
            }
        }

        if (minVal < arr[x][y]) {
            parent[x * c + y] = minX * c + minY;
            if (parent[minX * c + minY] == minX * c + minY) {
                dfs(minX, minY);
            }
        }
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static boolean check(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}









