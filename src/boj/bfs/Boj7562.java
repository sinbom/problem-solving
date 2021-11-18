package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7562 {

    public static class Node {

        private final int x;

        private final int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static int[][] graph;

    public static int[] dx = {-2, -1, 1, 2, 1, 2, -1, -2};

    public static int[] dy = {1, 2, 2, 1, -2, -1, -2, -1};

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int t = Integer.parseInt(stringTokenizer.nextToken());

            while (t-- > 0) {
                int l = Integer.parseInt(reader.readLine());
                graph = new int[l][l];
                String[] s = reader.readLine().split(" ");
                Node current = new Node(
                        Integer.parseInt(s[0]),
                        Integer.parseInt(s[1])
                );
                s = reader.readLine().split(" ");
                int destX = Integer.parseInt(s[0]);
                int destY = Integer.parseInt(s[1]);
                graph[current.x][current.y] = 1;
                bfs(current);
                writer.write(graph[destX][destY] > 0 ? graph[destX][destY] - 1 + "\n" : "0\n");
            }

        }
    }

    public static void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            node = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= graph.length || ny >= graph[0].length) {
                    continue;
                }

                if (graph[nx][ny] == 0) {
                    graph[nx][ny] = graph[node.x][node.y] + 1;
                    queue.offer(new Node(nx, ny));
                }
            }
        }
    }

}