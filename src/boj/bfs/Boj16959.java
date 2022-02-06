package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16959 {

    public static int[][][] dir = {
            {
                    {-1, -1},
                    {-1, 1},
                    {1, 1},
                    {1, -1}
            },
            {
                    {-1, -2},
                    {-2, -1},
                    {-2, 1},
                    {-1, 2},
                    {1, 2},
                    {2, 1},
                    {2, -1},
                    {1, -2}
            },
            {
                    {-1, 0},
                    {1, 0},
                    {0, -1},
                    {0, 1}
            }
    };

    public static final int BISHOP = 0;

    public static final int KNIGHT = 1;

    public static final int ROOK = 2;

    public static Queue<Node> queue;

    public static int[][] map;

    public static boolean[][][][] visited;

    public static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());

            queue = new LinkedList<>();
            map = new int[n][n];
            visited = new boolean[n][n][3][n * n + 1];

            for (int r = 0; r < n; ++r) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int c = 0; c < n; ++c) {
                    map[r][c] = Integer.parseInt(stringTokenizer.nextToken());
                    if (map[r][c] == 1) {
                        for (int i = 0; i < 3; ++i) {
                            queue.offer(new Node(r, c, i, 0, 2));
                            visited[r][c][i][2] = true;
                        }
                    }
                }
            }

            writer.write(bfs() + "");
        }
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.next == n * n + 1) {
                return current.time;
            }

            for (int i = 0; i < 3; i++) {
                if (current.type == i || visited[current.r][current.c][i][current.next]) {
                    continue;
                }

                Node next = new Node(current.r, current.c, i, current.time + 1, current.next);
                queue.offer(next);
                visited[current.r][current.c][i][current.next] = true;
            }

            switch (current.type) {
                case BISHOP:
                    for (int d = 0; d < 4; d++) {
                        int nr = current.r + dir[BISHOP][d][0];
                        int nc = current.c + dir[BISHOP][d][1];

                        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                            if (!visited[nr][nc][BISHOP][current.next]) {
                                visited[nr][nc][BISHOP][current.next] = true;

                                if (map[nr][nc] == current.next) {
                                    queue.offer(new Node(nr, nc, BISHOP, current.time + 1, current.next + 1));
                                } else {
                                    queue.offer(new Node(nr, nc, BISHOP, current.time + 1, current.next));
                                }
                            }

                            nr += dir[BISHOP][d][0];
                            nc += dir[BISHOP][d][1];
                        }
                    }
                    break;
                case KNIGHT:
                    for (int d = 0; d < 8; d++) {
                        int nr = current.r + dir[KNIGHT][d][0];
                        int nc = current.c + dir[KNIGHT][d][1];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc][KNIGHT][current.next]) {
                            continue;
                        }

                        visited[nr][nc][KNIGHT][current.next] = true;

                        if (map[nr][nc] == current.next) {
                            queue.offer(new Node(nr, nc, KNIGHT, current.time + 1, current.next + 1));
                        } else {
                            queue.offer(new Node(nr, nc, KNIGHT, current.time + 1, current.next));
                        }
                    }
                    break;
                case ROOK:
                    for (int d = 0; d < 4; d++) {
                        int nr = current.r + dir[ROOK][d][0];
                        int nc = current.c + dir[ROOK][d][1];

                        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                            if (!visited[nr][nc][ROOK][current.next]) {
                                visited[nr][nc][ROOK][current.next] = true;

                                if (map[nr][nc] == current.next) {
                                    queue.offer(new Node(nr, nc, ROOK, current.time + 1, current.next + 1));
                                } else {
                                    queue.offer(new Node(nr, nc, ROOK, current.time + 1, current.next));
                                }
                            }

                            nr += dir[ROOK][d][0];
                            nc += dir[ROOK][d][1];
                        }
                    }
            }
        }

        return -1;
    }

    public static class Node {

        private final int r;
        private final int c;
        private final int type;
        private final int time;
        private final int next;

        Node(int r, int c, int type, int time, int next) {
            this.r = r;
            this.c = c;
            this.type = type;
            this.time = time;
            this.next = next;
        }
    }

}