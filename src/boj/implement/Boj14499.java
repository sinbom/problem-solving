package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14499 {

    public static int[][] map;

    public static int[] garo = new int[4];

    public static int[] sero = new int[4];

    public static int[] dx = {0, 0, 0, -1, 1};

    public static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int x = Integer.parseInt(s[2]);
            int y = Integer.parseInt(s[3]);
            int k = Integer.parseInt(s[4]);
            map = new int[n][m];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            while (k-- > 0) {
                int dir = Integer.parseInt(stringTokenizer.nextToken());
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                x = nx;
                y = ny;

                if (dir == 1) {
                    int temp = garo[0];
                    for (int i = 0; i < 3; i++) {
                        garo[i] = garo[i + 1];
                    }
                    garo[3] = temp;
                    sero[3] = garo[3];
                    garoPrint(x, y);
                } else if (dir == 2) {
                    int temp = garo[3];
                    for (int i = 3; i > 0; i--) {
                        garo[i] = garo[i - 1];
                    }
                    garo[0] = temp;
                    sero[3] = garo[3];
                    garoPrint(x, y);
                } else if (dir == 3) {
                    int temp = sero[3];
                    for (int i = 3; i > 0; i--) {
                        sero[i] = sero[i - 1];
                    }
                    sero[0] = temp;
                    garo[3] = sero[3];
                    seroPrint(x, y);
                } else {
                    int temp = sero[0];
                    for (int i = 0; i < 3; i++) {
                        sero[i] = sero[i + 1];
                    }
                    sero[3] = temp;
                    garo[3] = sero[3];
                    seroPrint(x, y);
                }
            }
        }
    }

    public static void seroPrint(int i, int j) {
        if (map[i][j] == 0) {
            map[i][j] = sero[1];
            garo[1] = sero[1];
        } else {
            sero[1] = map[i][j];
            garo[1] = sero[1];
            map[i][j] = 0;
        }
        System.out.println(sero[3]);
    }

    public static void garoPrint(int i, int j) {
        if (map[i][j] == 0) {
            map[i][j] = garo[1];
            sero[1] = garo[1];
        } else {
            garo[1] = map[i][j];
            sero[1] = garo[1];
            map[i][j] = 0;
        }
        System.out.println(sero[3]);
    }

}