package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj17136 {

    public static int[][] map;

    public static int[] paper = {0, 5, 5, 5, 5, 5};

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            map = new int[10][10];

            for (int i = 0; i < map.length; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            go(0, 0, 0);

            if (answer == Integer.MAX_VALUE) {
                answer = -1;
            }

            writer.write(answer + "");
        }
    }

    public static void go(int x, int y, int cnt) {
        if (x >= 9 && y > 9) {
            answer = Math.min(answer, cnt);

            return;
        }

        if (answer <= cnt) {
            return;
        }

        if (y > 9) {
            go(x + 1, 0, cnt);

            return;
        }

        if (map[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (paper[i] > 0 && isAttach(x, y, i)) {
                    attach(x, y, i, 0);
                    paper[i]--;
                    go(x, y + 1, cnt + 1);
                    attach(x, y, i, 1);
                    paper[i]++;
                }
            }
        } else {
            go(x, y + 1, cnt);
        }
    }

    public static void attach(int x, int y, int size, int state) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = state;
            }
        }
    }

    public static boolean isAttach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                    return false;
                }

                if (map[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

}






