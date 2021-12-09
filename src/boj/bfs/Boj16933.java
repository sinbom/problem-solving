package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16933 {

    public static int[][] map;

    public static int[][] visited;

    public static int n;

    public static int m;

    public static int k;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            k = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][m];
            visited = new int[n][m];

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = s.charAt(j) - '0';
                    if (map[i][j] == 0) {
                        visited[i][j] = -1;
                    }
                }
            }

            writer.write(bfs() + "");
        }
    }

    public static int bfs() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0, k, 1, 0});
        visited[0][0] = k;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[0] == n - 1 && poll[1] == m - 1) {
                return poll[3];
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];
                int nc = poll[3] + 1;
                int nl = 1 - poll[4];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] >= poll[2]) {
                    continue;
                }

                if (map[nx][ny] == 1 && poll[4] == 1) {
                    queue.add(new int[]{poll[0], poll[1], poll[2], nc, nl});
                    continue;
                }

                int nb = map[nx][ny] == 1 ? poll[2] - 1 : poll[2];

                visited[nx][ny] = poll[2];
                queue.add(new int[]{nx, ny, nb, nc, nl});
            }
        }

        return -1;
    }


}