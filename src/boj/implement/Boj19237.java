package boj.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj19237 {

    public static int n;

    public static int m;

    public static int k;

    public static int[][] restTime;

    public static int[][] smell;

    public static int[][][] priority;

    public static Shark[] shark;

    public static int[] dx = {0, -1, 1, 0, 0};

    public static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            k = Integer.parseInt(stringTokenizer.nextToken());
            restTime = new int[n + 1][n + 1];
            smell = new int[n + 1][n + 1];
            priority = new int[m + 1][5][4];
            shark = new Shark[m + 1];

            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= n; j++) {
                    int n = Integer.parseInt(stringTokenizer.nextToken());

                    if (n > 0) {
                        shark[n] = new Shark(i, j, 0);
                        restTime[i][j] = k;
                        smell[i][j] = n;
                    }
                }
            }
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= m; i++) {
                shark[i].d = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= 4; j++) {
                    stringTokenizer = new StringTokenizer(reader.readLine());
                    for (int k = 0; k < 4; k++) {
                        priority[i][j][k] = Integer.parseInt(stringTokenizer.nextToken());
                    }
                }
            }

            writer.write(solve() + "\n");
        }
    }

    public static int solve() {
        int time = 0;

        while (true) {
            int count = 0;
            int[][] tmp = new int[n + 1][n + 1];

            for (int i = 1; i <= m; i++) {
                if (shark[i] != null) {
                    count++;
                }
            }

            if (count == 1 && shark[1] != null) {
                return time;
            }

            if (time >= 1000) {
                return -1;
            }

            for (int i = 1; i <= m; i++) {
                if (shark[i] != null) {
                    moveShark(tmp, i);
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (restTime[i][j] > 0) {
                        restTime[i][j]--;
                    }
                    if (restTime[i][j] == 0) {
                        smell[i][j] = 0;
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (tmp[i][j] > 0) {
                        restTime[i][j] = k;
                        smell[i][j] = tmp[i][j];
                    }
                }
            }
            time++;
        }

    }

    public static void moveShark(int[][] tmp, int m) {
        int nr = 0;
        int nc = 0;
        int d = 0;
        boolean flag = false;

        for (int i = 0; i < 4; i++) {
            d = priority[m][shark[m].d][i];
            nr = shark[m].r + dx[d];
            nc = shark[m].c + dy[d];
            if ((1 <= nr && nr <= n) && (1 <= nc && nc <= n) && smell[nr][nc] == 0) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            for (int i = 0; i < 4; i++) {
                d = priority[m][shark[m].d][i];
                nr = shark[m].r + dx[d];
                nc = shark[m].c + dy[d];
                if ((1 <= nr && nr <= n) && (1 <= nc && nc <= n) && smell[nr][nc] == m) {
                    break;
                }
            }
        }

        if (tmp[nr][nc] == 0) {
            tmp[nr][nc] = m;
            shark[m].r = nr;
            shark[m].c = nc;
            shark[m].d = d;
        } else {
            shark[m] = null;
        }
    }

    public static class Shark {

        private int r;
        private int c;
        private int d;

        Shark(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

    }

}