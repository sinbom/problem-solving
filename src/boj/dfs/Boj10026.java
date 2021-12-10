package boj.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Boj10026 {

    public static int n;

    public static char[][] map;

    public static boolean[][] check;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            map = new char[n][n];
            check = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                map[i] = reader.readLine().toCharArray();
            }

            List<Predicate<int[]>> predicates = Arrays.asList(
                    pair -> map[pair[0]][pair[1]] == map[pair[2]][pair[3]],
                    pair -> {
                        char c = map[pair[0]][pair[1]];
                        char nc = map[pair[2]][pair[3]];

                        if (c == 'R' || c == 'G') {
                            return nc == 'R' || nc == 'G';
                        }

                        return c == nc;
                    }
            );

            for (Predicate<int[]> predicate : predicates) {
                int count = 0;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!check[i][j]) {
                            dfs(i, j, predicate);
                            count++;
                        }
                    }
                }

                for (int i = 0; i < n; i++) {
                    Arrays.fill(check[i], false);
                }

                writer.write(count + " ");
            }
        }
    }

    public static void dfs(int x, int y, Predicate<int[]> predicate) {
        check[x][y] = true;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue;
            }

            if (check[nx][ny] || !predicate.test(new int[]{x, y, nx, ny})) {
                continue;
            }

            dfs(nx, ny, predicate);
        }
    }

}