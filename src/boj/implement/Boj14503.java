package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14503 {

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int[][] map;

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            stringTokenizer = new StringTokenizer(reader.readLine());
            int r = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][m];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            map[r][c] = -1;
            answer++;

            while (true) {
                boolean flag = false;

                for (int i = 0; i < dx.length; i++) {
                    int nd = (d - 1 - i + dy.length) % dy.length;
                    int nr = r + dx[nd];
                    int nc = c + dy[nd];

                    if (nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length) {
                        continue;
                    }

                    if (map[nr][nc] == 0) {
                        map[nr][nc] = -1;
                        answer++;
                        flag = true;
                        r = nr;
                        c = nc;
                        d = nd;
                        break;
                    }
                }

                if (!flag) {
                    int nd = (d + 2) % dy.length;
                    int nr = r + dx[nd];
                    int nc = c + dy[nd];

                    if (nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length || map[nr][nc] == 1) {
                        break;
                    }

                    r = nr;
                    c = nc;
                }
            }

            writer.write(answer + "");
        }
    }

}