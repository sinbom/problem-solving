package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16985 {

    public static int[][][] map = new int[5][5][5];

    public static boolean[][][] visited = new boolean[5][5][5];

    public static int[] p = {0, 0, 0, 0, 0};

    public static int[] dx = {1, -1, 0, 0, 0, 0};

    public static int[] dy = {0, 0, 1, -1, 0, 0};

    public static int[] dh = {0, 0, 0, 0, 1, -1};

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    StringTokenizer stk = new StringTokenizer(reader.readLine());

                    for (int k = 0; k < 5; k++) {
                        map[i][j][k] = Integer.parseInt(stk.nextToken());
                    }
                }
            }

            nCr(0, new boolean[5]);

            writer.write(answer == Integer.MAX_VALUE ? "-1" : answer + "");
        }
    }

    public static void nCr(int cnt, boolean[] visited) {
        if (cnt == 5) {
            int[][][] nmap = new int[5][5][5];

            for (int i = 0; i < 5; i++) {
                int idx = p[i];

                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        nmap[i][x][y] = map[idx][x][y];
                    }
                }
            }
            brute(0, new int[5][5][5], nmap);
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (!visited[i]) {
                visited[i] = true;
                p[i] = cnt;
                nCr(cnt + 1, visited);
                p[i] = 0;
                visited[i] = false;
            }
        }
    }

    public static void brute(int height, int[][][] nmap, int[][][] map) {
        if (height == 5) {
            if (nmap[0][0][0] == 1 && nmap[4][4][4] == 1) {
                int tmp = bfs(nmap);

                if (tmp != -1) {
                    answer = Math.min(answer, tmp);
                }
            }

            return;
        }
        for (int cnt = 1; cnt <= 4; cnt++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (map[height][i][j] == 1) {
                        if (cnt == 1) {
                            nmap[height][j][4 - i] = 1;
                        } else if (cnt == 2) {
                            nmap[height][4 - i][4 - j] = 1;
                        } else if (cnt == 3) {
                            nmap[height][4 - j][i] = 1;
                        } else {
                            nmap[height][i][j] = 1;
                        }
                    }
                }
            }

            brute(height + 1, nmap, map);
            nmap[height] = new int[5][5];
        }
    }

    public static int bfs(int[][][] nmap) {
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[5][5][5];
        queue.add(new Node(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.height == 4 && current.x == 4 && current.y == 4) {
                return current.cnt;
            }

            if (!visited[current.height][current.x][current.y]) {
                visited[current.height][current.x][current.y] = true;

                for (int k = 0; k < 6; k++) {
                    int nh = current.height + dh[k];
                    int nx = current.x + dx[k];
                    int ny = current.y + dy[k];

                    if (check(nh, nx, ny) && nmap[nh][nx][ny] == 1 && !visited[nh][nx][ny]) {
                        queue.add(new Node(nh, nx, ny, current.cnt + 1));
                    }
                }
            }
        }

        return -1;
    }

    public static boolean check(int h, int x, int y) {
        return h >= 0 && h < 5 && x >= 0 && x < 5 && y >= 0 && y < 5;
    }

    public static class Node {

        private final int height;

        private final int x;

        private final int y;

        private final int cnt;

        public Node(int height, int x, int y, int cnt) {
            this.height = height;
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}








