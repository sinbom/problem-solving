package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj6087 {

    public static int n;

    public static int m;

    public static char[][] map;

    public static int[][] check;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            m = Integer.parseInt(stringTokenizer.nextToken());
            n = Integer.parseInt(stringTokenizer.nextToken());
            map = new char[n][m];
            check = new int[n][m];

            int sx = -1;
            int sy = -1;
            int ex = -1;
            int ey = -1;

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < s.length(); j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == 'C') {
                        if (sx == -1) {
                            sx = i;
                            sy = j;
                        } else {
                            ex = i;
                            ey = j;
                        }
                    }
                }
            }

            bfs(sx, sy, ex, ey);
            writer.write(answer + "");
        }
    }

    public static void bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx, sy, -1, 0});
        check[sx][sy] = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int d = poll[2];
            int c = poll[3];

            if (x == ex && y == ey) {
                answer = Math.min(answer, c);
                continue;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (check(nx, ny)) {
                    continue;
                }

                int nc = d != -1 && d != i ? c + 1 : c;

                if (check[nx][ny] != 0 && check[nx][ny] < nc) {
                    continue;
                }

                check[nx][ny] = nc;
                queue.add(new int[]{nx, ny, i, nc});
            }
        }
    }

    public static boolean check(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m || map[x][y] == '*';
    }

}