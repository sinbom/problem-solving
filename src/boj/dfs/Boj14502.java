package boj.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14502 {

    public static int map[][];

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int n;

    public static int m;

    public static int answer = 0;

    public static int count = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][m];
            int[][] copy = new int[n][m];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    copy[i][j] = map[i][j];
                }
            }

            brute(0, 0);
            writer.write(answer + "");
        }
    }

    public static void update() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count++;
                } else if (map[i][j] == 3) {
                    map[i][j] = 0; // 숙주가 아닌 감염 바이러스는 카운팅 후에 다시 초기화
                }
            }
        }

        answer = Math.max(answer, count);
    }

    public static void brute(int index, int count) {
        if (count == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 2) {
                        dfs(i, j);
                    }
                }
            }
            update();

            return;
        }

        for (int i = index; i < n * m; i++) {
            int x = i / m;
            int y = i % m;

            if (map[x][y] != 0) {
                continue;
            }

            map[x][y] = 1;
            brute(index + 1, count + 1);
            map[x][y] = 0;
        }
    }

    public static void dfs(int x, int y) {
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] != 0) {
                continue;
            }

            map[nx][ny] = 3; // 숙주 바이러스가 아닌 감염된 바이러스
            dfs(nx, ny);
        }
    }

}