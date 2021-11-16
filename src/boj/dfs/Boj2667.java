package boj.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Boj2667 {

    public static int[][] graph;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int count = 0;

    public static List<Integer> counts;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            graph = new int[n][n];
            counts = new ArrayList<>(((n + 1) / 2) * ((n + 1) / 2));

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Character.getNumericValue(s.charAt(j));
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 1) {
                        dfs(i, j);
                        if (count > 0) {
                            counts.add(count);
                        }
                        count = 0;
                    }
                }
            }
            Collections.sort(counts);

            writer.write(counts.size() + "\n");
            for (Integer c : counts) {
                writer.write(c + "\n");
            }
        }
    }

    public static void dfs(int x, int y) {
        graph[x][y] = 0;
        count++;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= graph.length || ny >= graph[0].length) {
                continue;
            }
            if (graph[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }

    }

}