package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1175 {

    public static int n;

    public static int m;

    public static char[][] map;

    public static int[] dy = {0, 0, 1, -1};

    public static int[] dx = {1, -1, 0, 0};

    public static Point start;

    public static Point c1;

    public static Point c2;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new char[n + 1][m + 1];
            boolean isC1Empty = true;

            for (int i = 1; i <= n; i++) {
                String str = reader.readLine();
                for (int j = 1; j <= m; j++) {
                    map[i][j] = str.charAt(j - 1);
                    if (map[i][j] == 'S') {
                        start = new Point(i, j);
                        map[i][j] = '.';
                    } else if (map[i][j] == 'C') {
                        if (isC1Empty) {
                            c1 = new Point(i, j);
                            isC1Empty = false;
                        } else {
                            c2 = new Point(i, j);
                        }
                        map[i][j] = '.';
                    }
                }
            }

            writer.write(bfs() + "");
        }
    }

    public static int bfs() {
        int ans = 987654321;
        Queue<State> queue = new LinkedList<>();
        boolean[][][][] visit = new boolean[n + 1][m + 1][4][3];

        queue.offer(new State(start.y, start.x, 0, -1, 0));
        visit[start.y][start.x][0][0] = true;
        visit[start.y][start.x][1][0] = true;
        visit[start.y][start.x][2][0] = true;
        visit[start.y][start.x][3][0] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.y == c1.y && current.x == c1.x) {
                if (current.status != 1) {
                    current.status++;
                }
            } else if (current.y == c2.y && current.x == c2.x) {
                if (current.status <= 1) {
                    current.status += 2;
                }
            }

            if (current.status == 3) {
                ans = current.time;
                break;
            }

            for (int i = 0; i < 4; i++) {
                if (current.dir == i) {
                    continue;
                }

                int ny = current.y + dy[i];
                int nx = current.x + dx[i];

                if (ny >= 1 && ny <= n && nx >= 1 && nx <= m && map[ny][nx] == '.' && !visit[ny][nx][i][current.status]) {
                    visit[ny][nx][i][current.status] = true;
                    queue.offer(new State(ny, nx, current.time + 1, i, current.status));
                }
            }
        }

        return ans == 987654321 ? -1 : ans;
    }

    public static class State {

        private final int x;

        private final int y;

        private final int time;

        private final int dir;

        private int status;

        public State(int y, int x, int time, int dir, int status) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.dir = dir;
            this.status = status;
        }
    }

    public static class Point {

        private final int x;

        private final int y;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}