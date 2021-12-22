package boj.implement;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17143 {

    public static int r;

    public static int c;

    public static Shark[][] map;

    public static int[] dx = {0, -1, 1, 0, 0};

    public static int[] dy = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            r = Integer.parseInt(stringTokenizer.nextToken());
            c = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            map = new Shark[r][c];

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int s = Integer.parseInt(stringTokenizer.nextToken());
                int d = Integer.parseInt(stringTokenizer.nextToken());
                int z = Integer.parseInt(stringTokenizer.nextToken());
                map[x][y] = new Shark(x, y, s, d, z);
            }

            int answer = 0;

            for (int i = 0; i < c; i++) {
                answer += fishing(i);
                move();
            }

            writer.write(answer + "");
        }

    }

    public static int fishing(int position) {
        for (int i = 0; i < r; i++) {
            if (map[i][position] != null) {
                int result = map[i][position].z;
                map[i][position] = null;
                return result;
            }
        }

        return 0;
    }

    public static void move() {
        Queue<Shark> queue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == null) {
                    continue;
                }
                queue.add(map[i][j]);
                map[i][j] = null;
            }
        }

        while (!queue.isEmpty()) {
            Shark shark = queue.poll();
            int nx = shark.x;
            int ny = shark.y;
            int nd = shark.d;
            int speed = calculateSpeed(shark.d, shark.s);

            for (int i = 0; i < speed; i++) {
                nx = nx + dx[nd];
                ny = ny + dy[nd];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                    nd = calculateOppositeDirection(nd);
                    nx = nx + dx[nd] * 2;
                    ny = ny + dy[nd] * 2;
                }
            }

            shark = new Shark(nx, ny, shark.s, nd, shark.z);

            if (map[nx][ny] == null) {
                map[nx][ny] = shark;
            } else {
                if (map[nx][ny].z < shark.z) {
                    map[nx][ny] = shark;
                }
            }
        }
    }

    public static int calculateOppositeDirection(int direction) {
        return direction % 2 == 0 ? direction - 1 : direction + 1;
    }

    public static int calculateSpeed(int direction, int speed) {
        if (direction == 1 || direction == 2) {
            return speed % ((r - 1) * 2);
        }

        return speed % ((c - 1) * 2);
    }

    public static class Shark {

        private final int x;
        private final int y;
        private final int s;
        private final int d;
        private final int z;

        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

}