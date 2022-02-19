package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15558 {

    public static int n, k;

    public static int[][] map;

    public static boolean[][] visited;

    public static int[] dx = {1, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            k = Integer.parseInt(stringTokenizer.nextToken());
            visited = new boolean[2][n];
            map = new int[2][n];

            for (int i = 0; i < 2; i++) {
                String line = reader.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            writer.write(bfs() + "");
        }
    }

    public static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int nx;
            int ny;

            for (int i = 0; i <= 2; i++) {
                if (i == 2) {
                    nx = current.x == 0 ? 1 : 0;
                    ny = current.y + k;

                    if (ny >= n) {
                        return 1;
                    }

                    if (ny > current.time && !visited[nx][ny] && map[nx][ny] != 0) {
                        visited[nx][ny] = true;
                        queue.add(new Node(nx, ny, current.time + 1));
                    }
                } else {
                    ny = current.y + dx[i];

                    if (ny >= n) {
                        return 1;
                    }

                    if (ny > current.time && !visited[current.x][ny] && map[current.x][ny] != 0) {
                        visited[current.x][ny] = true;
                        queue.add(new Node(current.x, ny, current.time + 1));
                    }
                }
            }
        }

        return 0;
    }

    public static class Node {

        private final int x;

        private final int y;

        private final int time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

}

