package boj.brute_force;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj17135 {

    public static int n;

    public static int m;

    public static int d;

    public static int[][] map;

    public static int[][] copy;

    public static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            d = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n + 1][m + 1];
            copy = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    copy[i][j] = map[i][j];
                }
            }

            go(1, m, 3, new ArrayList<>());
            writer.write(answer + "\n");
        }
    }

    public static void init() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                map[i][j] = copy[i][j];
            }
        }
    }

    public static int distance(int r1, int r2, int c1, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    public static void go(int start, int n, int r, List<Integer> archer) {
        if (r == 0) {
            init();
            attack(archer);

            return;
        }

        for (int i = start; i <= n; i++) {
            archer.add(i);
            go(i + 1, n, r - 1, archer);
            archer.remove(archer.size() - 1);
        }
    }

    public static void attack(List<Integer> archer) {
        int result = 0;

        for (int t = 1; t <= n; t++) {
            boolean[][] visited = new boolean[n + 1][m + 1];
            for (int k = 0; k < archer.size(); k++) {
                int temp = archer.get(k);
                int minD = Integer.MAX_VALUE;
                int minR = Integer.MAX_VALUE;
                int minC = Integer.MAX_VALUE;

                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= m; j++) {
                        if (map[i][j] == 1) {
                            if (minD >= distance(i, n + 1, j, temp)) {
                                if (minD > distance(i, n + 1, j, temp)) {
                                    minD = distance(i, n + 1, j, temp);
                                    minR = i;
                                    minC = j;
                                } else {
                                    if (minC > j) {
                                        minR = i;
                                        minC = j;
                                    }
                                }
                            }
                        }
                    }
                }
                if (minD <= d) {
                    visited[minR][minC] = true;
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (visited[i][j]) {
                        map[i][j] = 0;
                        result++;
                    }
                }
            }

            for (int i = 1; i <= m; i++) {
                map[n][i] = 0;
            }

            for (int i = n; i >= 1; i--) {
                for (int j = 1; j <= m; j++) {
                    map[i][j] = map[i - 1][j];
                }
            }
        }

        answer = Math.max(answer, result);
    }

}


