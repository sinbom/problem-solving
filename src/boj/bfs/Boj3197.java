package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3197 {

    public static class Node {

        private final int r;

        private final int c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    public static Queue<Node> q;

    public static Queue<Node> waterQ;

    public static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static char[][] map;

    public static boolean[][] visited;

    public static Node[] swan;

    public static int rr;

    public static int cc;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            rr = Integer.parseInt(stringTokenizer.nextToken());
            cc = Integer.parseInt(stringTokenizer.nextToken());
            waterQ = new LinkedList<>();
            q = new LinkedList<>();
            swan = new Node[2];
            map = new char[rr][cc];
            visited = new boolean[rr][cc];
            int swanIdx = 0;

            for (int r = 0; r < rr; ++r) {
                char[] line = reader.readLine().toCharArray();
                for (int c = 0; c < cc; ++c) {
                    map[r][c] = line[c];
                    if (map[r][c] == 'L') {
                        swan[swanIdx++] = new Node(r, c);
                    }
                    if (map[r][c] != 'X') {
                        waterQ.offer(new Node(r, c));
                    }
                }
            }

            q.offer(swan[0]);
            visited[swan[0].r][swan[0].c] = true;

            int day = 0;
            boolean meet = false;

            while (true) {
                Queue<Node> nextQ = new LinkedList<>();
                while (!q.isEmpty()) {
                    Node now = q.poll();

                    if (now.r == swan[1].r && now.c == swan[1].c) {
                        meet = true;
                        break;
                    }

                    for (int d = 0; d < 4; ++d) {
                        int nr = now.r + dir[d][0];
                        int nc = now.c + dir[d][1];

                        if (nr >= rr || nr < 0 || nc >= cc || nc < 0 || visited[nr][nc]) continue;

                        visited[nr][nc] = true;

                        if (map[nr][nc] == 'X') {
                            nextQ.offer(new Node(nr, nc));
                            continue;
                        }
                        q.offer(new Node(nr, nc));
                    }
                }

                if (meet) {
                    break;
                }

                q = nextQ;

                int size = waterQ.size();

                for (int i = 0; i < size; ++i) {
                    Node now = waterQ.poll();

                    for (int d = 0; d < 4; ++d) {
                        int nr = now.r + dir[d][0];
                        int nc = now.c + dir[d][1];

                        if (nr >= rr || nr < 0 || nc >= cc || nc < 0) continue;

                        if (map[nr][nc] == 'X') {
                            map[nr][nc] = '.';
                            waterQ.offer(new Node(nr, nc));
                        }
                    }
                }
                day++;
            }

            writer.write(day + "\n");
        }
    }
}
