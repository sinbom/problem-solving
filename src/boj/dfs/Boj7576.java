package boj.dfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7576 {

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

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            Queue<Node> queue = new LinkedList<>();
            graph = new int[m][n];

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    int v = Integer.parseInt(stringTokenizer.nextToken());
                    graph[i][j] = v;
                    if (v == 1) {
                        queue.offer(new Node(i, j));
                    }
                }
            }

            if (queue.size() == n * m) {
                writer.write("0");
                return;
            }

            bfs(queue);

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 0) {
                        writer.write("-1");
                        return;
                    }
                }
            }

            writer.write((answer > 0 ? answer - 1 : answer) + "");
        }
    }

    public static void bfs(Queue<Node> queue) {
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= graph.length || ny >= graph[0].length) {
                    continue;
                }

                if (graph[nx][ny] == 0) {
                    graph[nx][ny] = graph[node.x][node.y] + 1;
                    queue.offer(new Node(nx, ny));
                    answer = Math.max(answer, graph[nx][ny]);
                }
            }
        }
    }

}