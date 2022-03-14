package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1952 {

    public static int[] dx = {0, 1, 0, -1};

    public static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            boolean[][] visit = new boolean[m][n];
            int x = 0;
            int y = 0;
            int dir = 1;
            int cnt = 0;

            while (!visit[y][x]) {
                visit[y][x] = true;
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (0 > nx || nx >= n || 0 > ny || ny >= m || visit[ny][nx]) {
                    dir = dir == 3 ? 0 : dir + 1;
                    cnt++;
                }

                x = x + dx[dir];
                y = y + dy[dir];
            }

            writer.write(cnt - 1 + "");
        }
    }
}
