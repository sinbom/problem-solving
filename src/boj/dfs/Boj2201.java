package boj.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj2201 {

    public static int[] dx = {-1, 1, 0, 0};

    public static int[] dy = {0, 0, -1, 1};

    public static int[][] arr = new int[5][5];

    public static Set<String> set = new HashSet<>();

    public static int n = 5;

    public static void main(String args[]) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(i, j, 0, "");
                }
            }

            writer.write(set.size() + "");
        }
    }

    public static void dfs(int x, int y, int depth, String s) {
        if (depth == 6) {
            set.add(s);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue;
            }

            dfs(nx, ny, depth + 1, s + arr[x][y]);
        }

    }
}
