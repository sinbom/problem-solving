package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2178 {

    public static class Node {

        private final int x;

        private final int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static int[][] graph;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int count = 0;

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            graph = new int[n][m];

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    graph[i][j] = Character.getNumericValue(s.charAt(j));
                }
            }

            bfs(0, 0);
            writer.write(graph[n - 1][m - 1] + "");
        }

    }

    public static void bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= graph.length || ny >= graph[0].length) {
                    continue;
                }

                if (graph[nx][ny] == 1) {
                    queue.offer(new Node(nx, ny));
                    graph[nx][ny] = graph[node.x][node.y] + 1;
                }
            }
        }
    }

}