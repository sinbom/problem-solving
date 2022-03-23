package boj.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj20057 {

    public static int n;

    public static int[][] map;

    public static int[] dx = {0, 1, 0, -1};

    public static int[] dy = {-1, 0, 1, 0};

    public static long out = 0;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            tornado(0, 0, 1, 0, n / 2, n / 2);
            writer.write(out + "");
        }
    }

    public static void tornado(int corner, int count, int size, int direction, int x, int y) {
        if (x == 0 && y == 0) {
            return;
        }

        int nx = x + dx[direction];
        int ny = y + dy[direction];

        wind(x, y, nx, ny, direction);

        count++;

        if (count == size) {
            corner++;
            direction++;
            direction %= 4;
            count = 0;
        }

        if (corner == 2) {
            corner = 0;
            size++;
        }

        tornado(corner, count, size, direction, nx, ny);
    }

    public static void wind(int x, int y, int nx, int ny, int direction) {
        int now = map[nx][ny];
        int s1 = (int) (now * 0.01);
        int s2 = (int) (now * 0.02);
        int s5 = (int) (now * 0.05);
        int s7 = (int) (now * 0.07);
        int s10 = (int) (now * 0.1);
        int a = now - 2 * (s1 + s2 + s7 + s10) - s5;
        map[nx][ny] = 0;

        if (direction == 0 || direction == 2) {
            for (int i = 0; i < 2; i++) {
                int sx = x + dx[1 + 2 * i];
                int sy = y + dy[1 + 2 * i];
                if (check(sx, sy))
                    map[sx][sy] += s1;
                else
                    out += s1;
            }

            for (int i = 0; i < 2; i++) {
                int sx = nx + dx[1 + 2 * i] * 2;
                int sy = ny + dy[1 + 2 * i] * 2;
                if (check(sx, sy))
                    map[sx][sy] += s2;
                else
                    out += s2;
            }

            for (int i = 0; i < 2; i++) {
                int sx = nx + dx[1 + 2 * i];
                int sy = ny + dy[1 + 2 * i];
                if (check(sx, sy))
                    map[sx][sy] += s7;
                else
                    out += s7;
            }

            for (int i = 0; i < 2; i++) {
                int sx = nx + dx[direction] + dx[1 + 2 * i];
                int sy = ny + dy[direction] + dy[1 + 2 * i];
                if (check(sx, sy))
                    map[sx][sy] += s10;
                else
                    out += s10;
            }

            int sx = nx + dx[direction] * 2;
            int sy = ny + dy[direction] * 2;

            if (check(sx, sy)) {
                map[sx][sy] += s5;
            } else {
                out += s5;
            }

            sx = nx + dx[direction];
            sy = ny + dy[direction];

            if (check(sx, sy)) {
                map[sx][sy] += a;
            } else {
                out += a;
            }
        } else {
            for (int i = 0; i < 2; i++) {
                int sx = x + dx[2 * i];
                int sy = y + dy[2 * i];
                if (check(sx, sy))
                    map[sx][sy] += s1;
                else
                    out += s1;
            }

            for (int i = 0; i < 2; i++) {
                int sx = nx + dx[2 * i] * 2;
                int sy = ny + dy[2 * i] * 2;
                if (check(sx, sy))
                    map[sx][sy] += s2;
                else
                    out += s2;
            }

            for (int i = 0; i < 2; i++) {
                int sx = nx + dx[2 * i];
                int sy = ny + dy[2 * i];
                if (check(sx, sy))
                    map[sx][sy] += s7;
                else
                    out += s7;
            }

            for (int i = 0; i < 2; i++) {
                int sx = nx + dx[direction] + dx[2 * i];
                int sy = ny + dy[direction] + dy[2 * i];
                if (check(sx, sy))
                    map[sx][sy] += s10;
                else
                    out += s10;
            }

            int sx = nx + dx[direction] * 2;
            int sy = ny + dy[direction] * 2;

            if (check(sx, sy)) {
                map[sx][sy] += s5;
            } else {
                out += s5;
            }

            sx = nx + dx[direction];
            sy = ny + dy[direction];

            if (check(sx, sy)) {
                map[sx][sy] += a;
            } else {
                out += a;
            }

        }
    }

    public static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

}


