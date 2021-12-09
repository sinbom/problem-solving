package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Boj16954 {

    public static char[][] map = new char[8][8];

    public static boolean[][][] check = new boolean[8][8][100];

    public static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1, 0};

    public static int[] dy = {0, 1, 0, -1, 1, 1, -1, -1, 0};

    public static final char[] EMPTY = {'.', '.', '.', '.', '.', '.', '.', '.'};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int i = 0; i < 8; i++) {
                map[i] = reader.readLine().toCharArray();
            }

            writer.write(bfs() ? "1" : "0");
        }
    }

    public static void move() {
        for (int i = 7; i > 0; i--) {
            map[i] = map[i - 1];
        }
        map[0] = EMPTY;
    }

    public static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{7, 0, 0});
        check[7][0][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];

                if (x == 0 && y == 7) {
                    return true;
                }

                if (map[x][y] == '#') {
                    continue;
                }

                for (int i = 0; i < dx.length; i++) {
                    int nx = poll[0] + dx[i];
                    int ny = poll[1] + dy[i];
                    int nc = poll[2] + 1;

                    if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8 || map[nx][ny] == '#' || check[nx][ny][nc]) {
                        continue;
                    }

                    check[nx][ny][nc] = true;
                    queue.add(new int[]{nx, ny, nc});
                }
            }
            move();
        }

        return false;
    }


}