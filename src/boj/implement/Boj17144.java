package boj.implement;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17144 {

    public static int r;

    public static int c;

    public static int[][] map;

    public static int cleaner = -1;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            r = Integer.parseInt(stringTokenizer.nextToken());
            c = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[r][c];
            int t = Integer.parseInt(stringTokenizer.nextToken());
            int cleaner = -1;
            Queue<Dust> queue = new LinkedList<>();

            for (int i = 0; i < r; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < c; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    if (map[i][j] == -1) {
                        if (cleaner == -1) {
                            cleaner = i;
                        }
                    } else if (map[i][j] > 4) {
                        queue.add(new Dust(i, j, map[i][j]));
                    }
                }
            }

            int answer = 0;

            while (t-- > 0) {
                spread(queue);
                wind(cleaner);
                answer = pushAndSum(queue);
            }

            writer.write(answer + "");
        }
    }

    public static int pushAndSum(Queue<Dust> queue) {
        int sum = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                }
                if (map[i][j] > 4) {
                    queue.add(new Dust(i, j, map[i][j]));
                }
            }
        }

        return sum;
    }

    public static void wind(int cleaner) {
        for (int i = cleaner - 2; i > -1; i--) {
            map[i + 1][0] = map[i][0];
        }
        for (int i = cleaner + 1 + 2; i < r; i++) {
            map[i - 1][0] = map[i][0];
        }

        for (int i = 1; i < c; i++) {
            map[0][i - 1] = map[0][i];
            map[r - 1][i - 1] = map[r - 1][i];
        }

        for (int i = 1; i <= cleaner; i++) {
            map[i - 1][c - 1] = map[i][c - 1];
        }
        for (int i = r - 2; i >= cleaner + 1; i--) {
            map[i + 1][c - 1] = map[i][c - 1];
        }

        for (int i = c - 2; i > 0; i--) {
            map[cleaner][i + 1] = map[cleaner][i];
            map[cleaner + 1][i + 1] = map[cleaner + 1][i];
        }
        map[cleaner][1] = 0;
        map[cleaner + 1][1] = 0;
    }

    public static void spread(Queue<Dust> queue) {
        while (!queue.isEmpty()) {
            Dust dust = queue.poll();
            int value = dust.n / 5;
            int total = 0;

            for (int i = 0; i < dx.length; i++) {
                int nx = dust.x + dx[i];
                int ny = dust.y + dy[i];

                if (outside(nx, ny) || map[nx][ny] == -1) {
                    continue;
                }

                map[nx][ny] += value;
                total += value;
            }
            map[dust.x][dust.y] -= total;
        }
    }

    public static boolean outside(int x, int y) {
        return x < 0 || y < 0 || x >= r || y >= c;
    }

    public static class Dust {

        private final int x;
        private final int y;
        private final int n;

        public Dust(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }

    }

}