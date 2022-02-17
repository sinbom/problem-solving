package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15653 {

    public static Queue<Node> queue = new LinkedList<>();

    static boolean[][][][] visited;

    public static int[][] dir = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public static char[][] map;

    public static int blueR;

    public static int blueC;

    public static int redR;

    public static int redC;

    public static int n;

    public static int m;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            visited = new boolean[n][m][n][m];
            map = new char[n][m];

            for (int r = 0; r < n; ++r) {
                char[] line = reader.readLine().toCharArray();
                for (int c = 0; c < m; ++c) {
                    if (line[c] == 'R') {
                        map[r][c] = '.';
                        redR = r;
                        redC = c;
                        queue.offer(new Node(r, c, line[c], 0));
                    } else if (line[c] == 'B') {
                        map[r][c] = '.';
                        blueR = r;
                        blueC = c;
                        queue.offer(new Node(r, c, line[c], 0));
                    } else {
                        map[r][c] = line[c];
                    }
                }
            }

            visited[redR][redC][blueR][blueC] = true;
            writer.write(bfs() + "");
        }
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            Node blue;
            Node red;

            if (queue.peek().type == 'B') {
                blue = queue.poll();
                red = queue.poll();
            } else {
                red = queue.poll();
                blue = queue.poll();
            }

            boolean blueInHole;
            boolean redInHole;
            boolean afterRed;
            int bcr;
            int bcc;
            int rcr;
            int rcc;
            int bnr;
            int bnc;
            int rnr;
            int rnc;

            for (int d = 0; d < 4; ++d) {
                blueInHole = redInHole = afterRed = false;
                bcr = blue.r;
                bcc = blue.c;
                rcr = red.r;
                rcc = red.c;

                while (true) {
                    bnr = bcr + dir[d][0];
                    bnc = bcc + dir[d][1];

                    if (bnr == rcr && bnc == rcc) {
                        afterRed = true;
                    }

                    if (map[bnr][bnc] == '#') {
                        break;
                    }

                    if (map[bnr][bnc] == 'O') {
                        blueInHole = true;
                        break;
                    }

                    bcr = bnr;
                    bcc = bnc;
                }

                while (true) {
                    rnr = rcr + dir[d][0];
                    rnc = rcc + dir[d][1];

                    if (map[rnr][rnc] == '#') {
                        break;
                    }

                    if (map[rnr][rnc] == 'O') {
                        redInHole = true;
                        break;
                    }

                    rcr = rnr;
                    rcc = rnc;
                }

                if (blueInHole) {
                    continue;
                }

                if (redInHole) {
                    return red.time + 1;
                }

                if (bcr == rcr && bcc == rcc) {
                    if (afterRed) {
                        bcr -= dir[d][0];
                        bcc -= dir[d][1];
                    } else {
                        rcr -= dir[d][0];
                        rcc -= dir[d][1];
                    }
                }

                if (visited[rcr][rcc][bcr][bcc]) {
                    continue;
                }

                visited[rcr][rcc][bcr][bcc] = true;
                queue.offer(new Node(bcr, bcc, 'B', blue.time + 1));
                queue.offer(new Node(rcr, rcc, 'R', red.time + 1));
            }
        }

        return -1;
    }

    public static class Node {

        private final int r;

        private final int c;

        private final int time;

        private final char type;

        Node(int r, int c, char type, int time) {
            this.r = r;
            this.c = c;
            this.type = type;
            this.time = time;
        }
    }

}