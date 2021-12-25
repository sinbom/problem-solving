package boj.implement;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17822 {

    public static int[][] map = new int[50][50];

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static Queue<Point> queue = new LinkedList<>();

    public static int n;

    public static int m;

    public static int t;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            t = Integer.parseInt(stringTokenizer.nextToken());

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            for (int i = 0; i < t; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int direction = Integer.parseInt(stringTokenizer.nextToken());
                int count = Integer.parseInt(stringTokenizer.nextToken()) % m;

                rotate(x, count, direction);

                double psum = 0;
                int cnt = 0;

                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        if (map[j][k] > 0) {
                            queue.add(new Point(j, k, map[j][k]));
                            psum += map[j][k];
                            cnt++;
                        }
                    }
                }
                if (!bfs()) {
                    psum /= cnt;
                    update(psum);
                }
            }

            int answer = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    answer += map[i][j];
                }
            }

            writer.write(answer + "");
        }
    }

    public static boolean bfs() {
        int size = queue.size();
        boolean flag = false;

        for (int i = 0; i < size; i++) {
            Point point = queue.poll();
            for (int j = 0; j < dx.length; j++) {
                int nx = dx[j] + point.x;
                int ny = dy[j] + point.y;

                if (j == 0 && point.x == 0) {
                    nx = m - 1;
                }

                if (j == 2 && point.x == m - 1) {
                    nx = 0;
                }

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }

                if (map[ny][nx] != point.v) {
                    continue;
                }

                flag = true;
                map[point.y][point.x] = 0;
                map[ny][nx] = 0;
            }
        }
        return flag;
    }


    public static void rotate(int x, int count, int direction) {
        for (int i = x; i <= n; i += x) {
            int n = i - 1;
            if (direction == 1) {
                int[] a = Arrays.copyOfRange(map[n], 0, count);
                int[] b = Arrays.copyOfRange(map[n], count, m);

                System.arraycopy(b, 0, map[n], 0, b.length);
                System.arraycopy(a, 0, map[n], b.length, a.length);
            } else {
                int[] a = Arrays.copyOfRange(map[n], 0, m - count);
                int[] b = Arrays.copyOfRange(map[n], m - count, m);

                System.arraycopy(b, 0, map[n], 0, b.length);
                System.arraycopy(a, 0, map[n], b.length, a.length);
            }
        }
    }

    public static void update(double sum) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    if (map[i][j] > sum) {
                        map[i][j] -= 1;
                    } else if (map[i][j] < sum) {
                        map[i][j] += 1;
                    }
                }
            }
        }
    }

    public static class Point {
        private final int x;
        private final int y;
        private final int v;

        Point(int y, int x, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }


}