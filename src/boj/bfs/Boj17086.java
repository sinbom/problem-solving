package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17086 {

    public static int n;

    public static int m;

    public static int[][] map;

    public static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};

    public static int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][m];
            Queue<int[]> queue = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    if (map[i][j] == 1) {
                        queue.add(new int[]{i, j});
                    }
                }
            }
            bfs(queue);

            int answer = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    answer = Math.max(answer, map[i][j]);
                }
            }

            writer.write(answer - 1 + "");
        }
    }

    public static void bfs(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cx = poll[0];
            int cy = poll[1];

            for (int i = 0; i < dx.length; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int cc = map[cx][cy] + 1;

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (map[nx][ny] != 0) {
                    continue;
                }

                map[nx][ny] = cc;
                queue.add(new int[]{nx, ny});
            }
        }
    }

}