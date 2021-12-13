package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj17141 {

    public static int[][] map;

    public static Set<Node> set;

    public static int n;

    public static int m;

    public static List<Integer> answers = new ArrayList<>();

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static final int EMPTY = 0;

    public static final int VIRUS = 2;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][n];
            set = new HashSet<>(m);

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    if (map[i][j] == 2) {
                        set.add(new Node(i, j));
                        map[i][j] = 0;
                    }
                }
            }

            combination(map, 0, new LinkedList<>());

            int result = Integer.MAX_VALUE;

            for (Integer answer : answers) {
                if (answer > -1 && answer < result) {
                    result = answer;
                }
            }

            writer.write(result == Integer.MAX_VALUE ? "-1" : result + "");
        }
    }

    public static void combination(int[][] arr, int index, List<Node> nodes) {
        if (nodes.size() == m) {
            answers.add(bfs(arr, nodes));
            return;
        }

        int[][] copy = copy(arr);

        for (int i = index; i < n * n; i++) {
            int x = i / n;
            int y = i % n;
            Node node = new Node(x, y);

            if (copy[x][y] != EMPTY || !set.contains(node)) {
                continue;
            }

            copy[x][y] = VIRUS;
            nodes.add(node);
            combination(copy, i + 1, nodes);
            nodes.remove(nodes.size() - 1);
            copy[x][y] = EMPTY;
        }
    }

    public static int[][] copy(int[][] arr) {
        int[][] copy = new int[n][n];

        for (int i = 0; i < n; i++) {
            copy[i] = arr[i].clone();
        }

        return copy;
    }

    public static int[][] createArr() {
        int[][] arr = new int[n][n];

        for (int[] ints : arr) {
            Arrays.fill(ints, -1);
        }

        return arr;
    }

    public static int bfs(int[][] map, List<Node> nodes) {
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

                if (map[nx][ny] != EMPTY || visited[nx][ny] != -1) {
                    continue;
                }

                visited[nx][ny] = visited[x][y] + 1;
                queue.add(new Node(nx, ny));
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == EMPTY && visited[i][j] == -1) {
                    return -1;
                }
                result = Math.max(result, visited[i][j]);
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

    }

}