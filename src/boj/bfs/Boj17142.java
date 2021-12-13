package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj17142 {

    public static int[][] map;

    public static int n;

    public static int m;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static final int EMPTY = 0;

    public static final int WALL = 1;

    public static final int VIRUS = 2;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            List<Integer> answers = new LinkedList<>();
            int result = Integer.MAX_VALUE;

            combination(0, new ArrayList<>(m), answers);

            for (Integer answer : answers) {
                if (answer > -1 && answer < result) {
                    result = answer;
                }
            }

            writer.write(result == Integer.MAX_VALUE ? "-1" : result + "");
        }
    }

    public static void combination(int index, List<Node> nodes, List<Integer> answers) {
        if (nodes.size() == m) {
            answers.add(bfs(nodes));
            return;
        }

        for (int i = index; i < n * n; i++) {
            int x = i / n;
            int y = i % n;
            Node node = new Node(x, y);

            if (map[x][y] != VIRUS) {
                continue;
            }

            nodes.add(node);
            combination(i + 1, nodes, answers);
            nodes.remove(nodes.size() - 1);
        }
    }

    public static int[][] createArr() {
        int[][] arr = new int[n][n];

        for (int[] ints : arr) {
            Arrays.fill(ints, -1);
        }

        return arr;
    }

    public static int bfs(List<Node> nodes) {
        Queue<Node> queue = new LinkedList<>();
        int[][] visited = createArr();

        for (Node node : nodes) {
            queue.add(node);
            visited[node.x][node.y] = 0;
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int x = current.x;
            int y = current.y;

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                if (map[nx][ny] == WALL || visited[nx][ny] != -1) {
                    continue;
                }

                Node next = new Node(nx, ny);
                visited[nx][ny] = visited[x][y] + 1;
                queue.add(next);

                if (map[nx][ny] == VIRUS) {
                    queue.add(next);
                }
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == EMPTY && visited[i][j] == -1) {
                    return -1;
                }
                if (map[i][j] != VIRUS && result < visited[i][j]) {
                    result = visited[i][j];
                }
            }
        }

        return result;
    }

    public static class Node {

        private final int x;
        private final int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}