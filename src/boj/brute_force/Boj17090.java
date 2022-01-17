package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj17090 {

    public static int[][] map, visited;

    public static boolean[] result;

    public static int n;

    public static int m;

    public static int num = 1;

    public static int answer;

    public static int[][] dt = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new int[n][m];
            visited = new int[n][m];
            result = new boolean[n * m + 1];

            for (int i = 0; i < n; i++) {
                String input = reader.readLine();

                for (int j = 0; j < m; j++) {
                    char c = input.charAt(j);

                    if (c == 'U') {
                        map[i][j] = 0;
                    } else if (c == 'R') {
                        map[i][j] = 1;
                    } else if (c == 'D') {
                        map[i][j] = 2;
                    } else {
                        map[i][j] = 3;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j] != 0) {
                        continue;
                    }

                    dfs(i, j, 0);
                    num++;
                }
            }

            writer.write(answer + "");
        }
    }

    public static void dfs(int x, int y, int count) {
        if (check(x, y)) {
            result[num] = true;
            answer += count;

            return;
        } else if (visited[x][y] != 0) {
            if (result[visited[x][y]]) {
                result[num] = true;
                answer += count;
            }

            return;
        }

        visited[x][y] = num;
        dfs(x + dt[map[x][y]][0], y + dt[map[x][y]][1], count + 1);
    }

    public static boolean check(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

}








