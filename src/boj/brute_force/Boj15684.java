package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj15684 {

    public static int width;

    public static int height;

    public static int m;

    public static int answer;

    public static int[][] map;

    public static boolean isFinish = false;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            width = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            height = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[height + 1][width + 1];

            for (int y = 0; y < m; y++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                map[a][b] = 1;
                map[a][b + 1] = 2;
            }

            for (int i = 0; i <= 3; i++) {
                answer = i;
                go(1, 1, 0);

                if (isFinish) {
                    break;
                }
            }

            writer.write(isFinish ? answer + "" : "-1");
        }
    }

    public static void go(int x, int y, int count) {
        if (isFinish) {
            return;
        }

        if (answer == count) {
            if (check()) {
                isFinish = true;
            }

            return;
        }

        for (int i = y; i <= height; i++) {
            for (int j = x; j < width; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    go(1, 1, count + 1);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    public static boolean check() {
        for (int i = 1; i <= width; i++) {
            int nx = i;
            int ny = 1;

            while (ny <= height) {
                if (map[ny][nx] == 1) {
                    nx++;
                } else if (map[ny][nx] == 2) {
                    nx--;
                }
                ny++;
            }

            if (nx != i) {
                return false;
            }
        }

        return true;
    }
}






