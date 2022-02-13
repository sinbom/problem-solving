package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj16920 {

    public static int n;

    public static int m;

    public static int p;

    public static int[] s;

    public static List<List<Point>> starts;

    public static int[] cnt;

    public static boolean[][] visited;

    public static int[] dx = {-1, 1, 0, 0};

    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            p = Integer.parseInt(stringTokenizer.nextToken());
            starts = new ArrayList<>(p + 1);
            for (int i = 0; i <= p; i++) {
                starts.add(new ArrayList<>());
            }

            s = new int[p + 1];
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= p; i++) {
                s[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            visited = new boolean[n][m];
            cnt = new int[p + 1];
            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    char c = s.charAt(j);
                    if (c == '#') {
                        visited[i][j] = true;
                    } else if (c != '.') {
                        starts.get(c - '0').add(new Point(i, j, 0));
                        visited[i][j] = true;
                        cnt[c - '0']++;
                    }
                }
            }

            while (true) {
                boolean endFlag = false;
                for (int i = 1; i <= p; i++) {
                    endFlag = bfs(i) || endFlag;
                }

                if (!endFlag) {
                    break;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= p; i++) {
                sb.append(cnt[i]).append(" ");
            }

            writer.write(sb.toString().trim());
        }
    }

    public static boolean bfs(int idx) {
        Queue<Point> queue = new ArrayDeque<>();
        for (Point start : starts.get(idx)) {
            queue.offer(start);
        }
        starts.get(idx).clear();

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.dist == s[idx]) {
                current.setDist();
                starts.get(idx).add(current);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (check(nx, ny) && !visited[nx][ny] && current.dist < s[idx]) {
                    Point next = new Point(nx, ny, current.dist + 1);
                    queue.offer(next);
                    visited[nx][ny] = true;
                    cnt[idx]++;
                }
            }
        }

        return !starts.get(idx).isEmpty();
    }

    public static boolean check(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static class Point {
        private final int x;
        private final int y;
        private int dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public void setDist() {
            this.dist = 0;
        }
    }
}