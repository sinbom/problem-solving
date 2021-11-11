package boj.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16173 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[][] graph = new int[n][n];
            boolean[][] check = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            dfs(graph, check, 0, 0);
            writer.write(check[n - 1][n - 1] ? "HaruHaru" : "Hing");
        }
    }

    public static void dfs(int[][] graph, boolean[][] check, int x, int y) {
        if (x >= graph.length || y >= graph[0].length) {
            return;
        }

        if (check[x][y] || check[check.length - 1][check[0].length - 1]) {
            return;
        }

        check[x][y] = true;
        dfs(graph, check, x + graph[x][y], y);
        dfs(graph, check, x, y + graph[x][y]);
    }

}