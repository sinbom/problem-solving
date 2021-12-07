package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16948 {

    public static boolean[][] check;

    public static int[] dx = {-2, -2, 0, 0, 2, 2};

    public static int[] dy = {-1, 1, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            check = new boolean[n][n];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int r1 = Integer.parseInt(stringTokenizer.nextToken());
            int c1 = Integer.parseInt(stringTokenizer.nextToken());
            int r2 = Integer.parseInt(stringTokenizer.nextToken());
            int c2 = Integer.parseInt(stringTokenizer.nextToken());

            writer.write(bfs(r1, c1, r2, c2) + "");
        }
    }

    public static int bfs(int r1, int c1, int r2, int c2) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r1, c1, 0});
        check[r1][c1] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[0] == r2 && poll[1] == c2) {
                return poll[2];
            }

            for (int i = 0; i < dx.length; i++) {
                int nr = poll[0] + dx[i];
                int nc = poll[1] + dy[i];
                int cnt = poll[2] + 1;

                if (nr < 0 || nc < 0 || nr >= check.length || nc >= check.length) {
                    continue;
                }

                if (!check[nr][nc]) {
                    check[nr][nc] = true;
                    queue.add(new int[]{nr, nc, cnt});
                }
            }
        }

        return -1;
    }

}