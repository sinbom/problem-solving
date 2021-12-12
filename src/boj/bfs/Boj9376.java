package boj.bfs;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9376 {

    public static int t;

    public static int n;

    public static int m;

    public static char[][] map;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            t = Integer.parseInt(reader.readLine());

            while (t-- > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                n = Integer.parseInt(stringTokenizer.nextToken()) + 2;
                m = Integer.parseInt(stringTokenizer.nextToken()) + 2;
                map = new char[n][m];

                int x = -1;
                int y = -1;
                int x2 = -1;
                int y2 = -1;

                for (int i = 1; i < n - 1; i++) {
                    String s = reader.readLine();
                    for (int j = 1; j < m - 1; j++) {
                        map[i][j] = s.charAt(j - 1);
                        if (map[i][j] == '$') {
                            if (x == -1) {
                                x = i;
                                y = j;
                            } else {
                                x2 = i;
                                y2 = j;
                            }
                        }
                    }
                }

                int[][] bfs = bfs(x, y);
                int[][] bfs2 = bfs(x2, y2);
                int[][] bfs3 = bfs(0, 0);
                int answer = Integer.MAX_VALUE;

                for (int i = 1; i < n - 1; i++) {
                    for (int j = 1; j < m - 1; j++) {
                        if (map[i][j] == '*') {
                            continue;
                        }
                        if (bfs[i][j] == -1) {
                            continue;
                        }
                        int sum = bfs[i][j] + bfs2[i][j] + bfs3[i][j];
                        if (map[i][j] == '#') {
                            sum -= 2;
                        }
                        answer = Math.min(answer, sum);
                    }
                }
                writer.write(answer + "\n");
            }
        }
    }

    public static int[][] bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[n][m];

        for (int[] ints : visited) {
            Arrays.fill(ints, -1);
        }

        queue.add(new int[]{x, y, 0});
        visited[x][y] = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            x = poll[0];
            y = poll[1];
            int c = poll[2];

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == '*') {
                    continue;
                }

                int nc = map[nx][ny] == '#' ? c + 1 : c;

                if (visited[nx][ny] != -1 && visited[nx][ny] <= nc) {
                    continue;
                }

                visited[nx][ny] = nc;
                queue.add(new int[]{nx, ny, nc});
            }
        }

        return visited;
    }

}