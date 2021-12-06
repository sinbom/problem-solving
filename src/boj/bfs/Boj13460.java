package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13460 {

    public static char[][] arr = new char[10][10];

    public static boolean[][][][] check = new boolean[10][10][10][10];

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int rx = 0;
            int ry = 0;
            int bx = 0;
            int by = 0;
            arr = new char[n][m];
            check = new boolean[n][m][n][m];

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    arr[i][j] = s.charAt(j);
                    if (arr[i][j] == 'R') {
                        rx = i;
                        ry = j;
                    } else if (arr[i][j] == 'B') {
                        bx = i;
                        by = j;
                    }
                }
            }

            writer.write(bfs(new int[]{rx, ry, bx, by, 0}) + "");
        }
    }

    public static int bfs(int[] positions) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(positions);
        check[positions[0]][positions[1]][positions[2]][positions[3]] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[4] >= 10) {
                break;
            }

            for (int i = 0; i < dx.length; i++) {
                int nrx = poll[0];
                int nry = poll[1];
                int nbx = poll[2];
                int nby = poll[3];
                int nc = poll[4] + 1;
                int rc = 0;
                int bc = 0;

                while (arr[nrx + dx[i]][nry + dy[i]] != '#' && arr[nrx][nry] != 'O') {
                    nrx += dx[i];
                    nry += dy[i];
                    rc++;
                }
                while (arr[nbx + dx[i]][nby + dy[i]] != '#' && arr[nbx][nby] != 'O') {
                    nbx += dx[i];
                    nby += dy[i];
                    bc++;
                }

                if (arr[nbx][nby] == 'O') {
                    continue;
                }
                if (arr[nrx][nry] == 'O') {
                    return nc;
                }

                if (nrx == nbx && nry == nby) {
                    if (rc > bc) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                if (!check[nrx][nry][nbx][nby]) {
                    check[nrx][nry][nbx][nby] = true;
                    queue.add(new int[]{nrx, nry, nbx, nby, nc});
                }
            }
        }

        return -1;
    }

}