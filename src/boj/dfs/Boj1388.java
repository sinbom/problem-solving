package boj.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1388 {

    public static boolean[][] check;

    public static int[] dx = {1, 0};

    public static int[] dy = {0, 1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            char[][] graph = new char[n][m];
            check = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    graph[i][j] = s.charAt(j);
                }
            }

            int answer = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (dfs(graph, i, j, 0) > 0) {
                        answer++;
                    }
                }
            }

            writer.write(answer + "");
        }
    }

    public static int dfs(char[][] graph, int x, int y, int count) {
        if (check[x][y]) {
            return count;
        }

        check[x][y] = true;
        count++;

        if (graph[x][y] == '-') {
            int ny = y + 1;
            if (ny < graph[0].length && graph[x][y] == graph[x][ny]) {
                count = dfs(graph, x, ny, count);
            }
        } else {
            int nx = x + 1;
            if (nx < graph.length && graph[x][y] == graph[nx][y]) {
                count = dfs(graph, nx, y, count);
            }
        }

        return count;
    }

}