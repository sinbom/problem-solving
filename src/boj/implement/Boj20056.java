package boj.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj20056 {

    public static int n;

    public static List<FireBall>[][] list;

    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            int M = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            list = new LinkedList[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    list[i][j] = new LinkedList<>();
                }
            }

            for (int i = 0; i < M; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int r = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int c = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int m = Integer.parseInt(stringTokenizer.nextToken());
                int s = Integer.parseInt(stringTokenizer.nextToken());
                int d = Integer.parseInt(stringTokenizer.nextToken());
                list[r][c].add(new FireBall(m, d, s));
            }

            for (int i = 0; i < k; i++) {
                move();
            }

            writer.write(sum() + "");
        }
    }

    public static void move() {
        LinkedList<FireBall>[][] next = new LinkedList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (list[i][j].size() >= 1) {
                    for (FireBall fireBall : list[i][j]) {
                        int distance = fireBall.s % n;
                        int x = i + dx[fireBall.d] * distance;
                        int y = j + dy[fireBall.d] * distance;

                        if (x >= n) {
                            x -= n;
                        } else if (x < 0) {
                            x += n;
                        }

                        if (y >= n) {
                            y -= n;
                        } else if (y < 0) {
                            y += n;
                        }

                        next[x][y].add(new FireBall(fireBall.m, fireBall.d, fireBall.s));
                    }
                }
            }
        }

        list = next;
        split();
    }

    public static void split() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (list[i][j].size() >= 2) {
                    int mSum = 0;
                    int sSum = 0;
                    boolean even = true;
                    boolean odd = true;

                    for (FireBall fb : list[i][j]) {
                        mSum += fb.m;
                        sSum += fb.s;
                        if (fb.d % 2 == 0) {
                            odd = false;
                        } else {
                            even = false;
                        }
                    }

                    int m = mSum / 5;
                    int s = sSum / list[i][j].size();
                    list[i][j].clear();

                    if (m > 0) {
                        for (int a = 0; a < 4; a++) {
                            if (odd || even) {
                                list[i][j].add(new FireBall(m, 2 * a, s));
                            } else {
                                list[i][j].add(new FireBall(m, 1 + 2 * a, s));
                            }
                        }
                    }
                }
            }
        }
    }

    public static long sum() {
        long sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (FireBall fb : list[i][j]) {
                    sum += fb.m;
                }
            }
        }

        return sum;
    }

    public static class FireBall {

        private final int m;

        private final int d;

        private final int s;

        FireBall(int m, int d, int s) {
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
}


