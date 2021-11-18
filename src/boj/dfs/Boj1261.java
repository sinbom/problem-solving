package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Boj1261 {

    public static int[][] graph;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            graph = new int[m][n];

            for (int i = 0; i < m; i++) {
                String line = reader.readLine();
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Character.getNumericValue(line.charAt(j));
                }
            }

            bfs();
        }
    }

    public static void bfs() {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        graph[0][0] = -1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int c = poll[2];

            if (x == graph.length - 1 && y == graph[0].length - 1) {
                System.out.println(c + "");
                return;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= graph.length || ny >= graph[0].length) {
                    continue;
                }

                if (graph[nx][ny] == 0) {
                    queue.addFirst(new int[]{nx, ny, c});
                } else if (graph[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny, c + 1});
                }

                graph[nx][ny] = -1;
            }
        }
    }

}