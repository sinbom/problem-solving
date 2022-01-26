package boj.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Boj17472 {

    public static int[] dx = {0, 0, 1, -1};

    public static int[] dy = {1, -1, 0, 0};

    public static int n;

    public static int m;

    public static int[][] map;

    public static boolean[][] visited;

    public static Queue<Edge> priorityQueue = new PriorityQueue<>();

    public static int island = 0;

    public static int result = 0;

    public static int[] parents;

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] str = reader.readLine().split(" ");
            n = Integer.parseInt(str[0]);
            m = Integer.parseInt(str[1]);
            map = new int[n][m];
            visited = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                str = reader.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(str[j]);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        island++;
                        bfs(new Point(i, j));
                    }
                }
            }

            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0) {
                        makeBridge(new Point(i, j), map[i][j]);
                    }
                }
            }

            parents = new int[island + 1];

            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }

            int size = priorityQueue.size();
            for (int i = 0; i < size; i++) {
                Edge tmp = priorityQueue.poll();

                int a = find(tmp.s);
                int b = find(tmp.e);

                if (a == b) {
                    continue;
                }

                union(tmp.s, tmp.e);
                result += tmp.v;
                count++;
            }

            if (result == 0 || count != island - 1) {
                writer.write("-1");
            } else {
                writer.write(result + "");
            }
        }
    }

    public static void bfs(Point d) {
        Queue<Point> queue = new LinkedList<>();
        visited[d.x][d.y] = true;
        map[d.x][d.y] = island;
        queue.add(d);

        while (!queue.isEmpty()) {
            Point t = queue.poll();
            int x = t.x;
            int y = t.y;

            for (int i = 0; i < 4; i++) {
                int x2 = x + dx[i];
                int y2 = y + dy[i];

                if (x2 < 0 || x2 >= n || y2 < 0 || y2 >= m || map[x2][y2] != 1 || visited[x2][y2]) {
                    continue;
                }

                queue.add(new Point(x2, y2));
                map[x2][y2] = island;
                visited[x2][y2] = true;
            }

        }
    }

    public static void makeBridge(Point d, int num) {
        int x2 = d.x;
        int y2 = d.y;
        int length = 0;

        for (int i = 0; i < 4; i++) {
            while (true) {
                x2 = x2 + dx[i];
                y2 = y2 + dy[i];

                if (x2 >= 0 && x2 < n && y2 >= 0 && y2 < m) {
                    if (map[x2][y2] == num) {
                        length = 0;
                        x2 = d.x;
                        y2 = d.y;
                        break;
                    } else if (map[x2][y2] == 0) {
                        length++;
                    } else {
                        if (length > 1) {
                            priorityQueue.add(new Edge(num, map[x2][y2], length));
                        }
                        length = 0;
                        x2 = d.x;
                        y2 = d.y;
                        break;
                    }
                } else {
                    length = 0;
                    x2 = d.x;
                    y2 = d.y;
                    break;
                }
            }
        }
    }

    public static int find(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    public static void union(int s, int e) {
        int aRoot = find(s);
        int bRoot = find(e);

        if (aRoot != bRoot) {
            parents[aRoot] = e;
        }
    }

    public static class Point {

        private final int x;

        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge> {

        public final int s;

        public final int e;

        public final int v;

        public Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(Edge edge) {
            return edge.v >= this.v ? -1 : 1;
        }
    }
}