package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14890 {

    public static int[][] map;

    public static int l;

    public static int n;

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] s = reader.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            l = Integer.parseInt(s[1]);
            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                garo(i);
                sero(i);
            }

            writer.write(answer + "");
        }
    }

    public static void garo(int i) {
        boolean[] check = new boolean[n];

        for (int j = 0; j < n; j++) {
            if (j + 1 < n) {
                if (map[i][j] - 1 > map[i][j + 1]) {
                    return;
                }
                if (map[i][j] + 1 < map[i][j + 1]) {
                    return;
                }

                if (map[i][j] - 1 == map[i][j + 1]) {
                    if (j + l >= n) {
                        return;
                    }
                    for (int k = j + 1, value = map[i][j + 1]; k <= j + l; k++) {
                        if (value != map[i][k] || check[k]) {
                            return;
                        }
                        check[k] = true;
                        value = map[i][k];
                    }
                    j = j + l - 1;
                } else if (map[i][j] + 1 == map[i][j + 1]) {
                    if (j - (l - 1) < 0) {
                        return;
                    }
                    for (int k = j - (l - 1), value = map[i][j - (l - 1)]; k <= j; k++) {
                        if (value != map[i][k] || check[k]) {
                            return;
                        }
                        check[k] = true;
                        value = map[i][k];
                    }
                }
            }
        }

        answer++;
    }

    public static void sero(int j) {
        boolean[] check = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (i + 1 < n) {
                if (map[i][j] + 1 < map[i + 1][j]) {
                    return;
                }
                if (map[i][j] - 1 > map[i + 1][j]) {
                    return;
                }

                if (map[i][j] - 1 == map[i + 1][j]) {
                    if (i + l >= n) {
                        return;
                    }
                    for (int k = i + 1, value = map[i + 1][j]; k <= i + l; k++) {
                        if (value != map[k][j] || check[k]) {
                            return;
                        }
                        check[k] = true;
                        value = map[k][j];
                    }
                    i = i + l - 1;
                } else if (map[i][j] + 1 == map[i + 1][j]) {
                    if (i - (l - 1) < 0) {
                        return;
                    }
                    for (int k = i - (l - 1), value = map[i - (l - 1)][j]; k <= i; k++) {
                        if (value != map[k][j] || check[k]) {
                            return;
                        }
                        check[k] = true;
                        value = map[k][j];
                    }
                }
            }
        }

        answer++;
    }

}