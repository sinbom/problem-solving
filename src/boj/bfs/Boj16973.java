package boj.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16973 {

    public static int n;

    public static int m;

    public static int[][] map;

    public static boolean[][] visit;

    public static int h;

    public static int w;

    public static int sx;

    public static int sy;

    public static int fx;

    public static int fy;

    public static int[] dx = {0, 0, 1, -1};

    public static int[] dy = {1, -1, 0, 0};

    public static int answer = 987654321;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n + 1][m + 1];
            visit = new boolean[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            stringTokenizer = new StringTokenizer(reader.readLine());
            h = Integer.parseInt(stringTokenizer.nextToken());
            w = Integer.parseInt(stringTokenizer.nextToken());
            sx = Integer.parseInt(stringTokenizer.nextToken());
            sy = Integer.parseInt(stringTokenizer.nextToken());
            fx = Integer.parseInt(stringTokenizer.nextToken());
            fy = Integer.parseInt(stringTokenizer.nextToken());

            writer.write(bfs() + "");
        }
    }

    public static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        visit[sx][sy] = true;
        queue.offer(new Node(sx, sy, 0));
        int answer = 987654321;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.x == fx && current.y == fy) {
                if (answer > current.cnt) {
                    answer = current.cnt;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx < 1 || ny < 1 || nx > n || ny > m || visit[nx][ny]) {
                    continue;

                }
                if (!check(nx, ny)) {
                    continue;
                }

                visit[nx][ny] = true;
                queue.offer(new Node(nx, ny, current.cnt + 1));
            }
        }

        return answer == 987654321 ? -1 : answer;
    }

    private static boolean check(int x, int y) {
        for (int i = x; i < x + h; i++) {
            for (int j = y; j < y + w; j++) {
                if (i < 1 || j < 1 || i > n || j > m || map[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public static class Node {

        private final int x;

        private final int y;

        private final int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

    }

}