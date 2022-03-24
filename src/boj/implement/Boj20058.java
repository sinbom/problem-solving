package boj.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj20058 {

    public static int n;

    public static int size;

    public static int sum;

    public static int[][] map;

    public static int[] dx = {-1, 0, 0, 1};

    public static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            int q = Integer.parseInt(stringTokenizer.nextToken());
            size = (int) Math.pow(2, n);
            map = new int[size][size];

            for (int i = 0; i < size; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < size; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < q; i++) {
                int l = Integer.parseInt(stringTokenizer.nextToken());
                rotate(l);
                melt();
            }

            boolean[][] visit = new boolean[size][size];
            int max = 0;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (!visit[i][j] && map[i][j] > 0) {
                        visit[i][j] = true;
                        max = Math.max(max, answer(i, j, visit));
                    }
                }
            }

            writer.write(sum + "\n" + max);
        }
    }

    public static void rotate(int l) {
        int loop = size / (int) Math.pow(2, l);
        int[][] next = new int[size][size];
        int x = 0;

        for (int i = 0; i < loop; i++) {
            int y = 0;

            if (i != 0) {
                x += (int) Math.pow(2, l);
            }

            for (int j = 0; j < loop; j++) {
                if (j != 0) {
                    y += (int) Math.pow(2, l);
                }

                for (int a = 0; a < (int) Math.pow(2, l); a++) {
                    for (int b = 0; b < (int) Math.pow(2, l); b++) {
                        next[x + b][y - a + (int) Math.pow(2, l) - 1] = map[x + a][y + b];
                    }
                }
            }
        }
        map = next;
    }

    public static void melt() {
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int count = 0;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (check(nx, ny))
                        if (map[nx][ny] >= 1)
                            count++;
                }

                if (count < 3) {
                    qx.offer(i);
                    qy.offer(j);
                }
            }
        }

        while (!qx.isEmpty()) {
            int x = qx.poll();
            int y = qy.poll();

            map[x][y]--;
        }
    }

    public static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < size && y < size;
    }

    public static int answer(int x, int y, boolean[][] visit) {
        int count = 1;

        sum += map[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (check(nx, ny) && map[nx][ny] > 0 && !visit[nx][ny]) {
                visit[nx][ny] = true;
                count += answer(nx, ny, visit);
            }
        }

        return count;
    }
}


