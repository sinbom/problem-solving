package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2206 {

    public static int[][] map;

    public static boolean[][][] check;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int n;

    public static int m;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][m];
            check = new boolean[n][m][2];

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = s.charAt(j) - '0';
                }
            }

            writer.write(bfs() + "");
        }
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0, 1});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[0] == n - 1 && poll[1] == m - 1) {
                return poll[3];
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];
                int nc = poll[3] + 1;

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (map[nx][ny] == 1 && poll[2] == 1) { // 벽인데 이미 벽을 부쉈으면
                    continue;
                }

                int nb = poll[2] + map[nx][ny];

                if (!check[nx][ny][nb]) {
                    check[nx][ny][nb] = true;
                    queue.add(new int[]{nx, ny, nb, nc});
                }
            }
        }

        return -1;
    }

}