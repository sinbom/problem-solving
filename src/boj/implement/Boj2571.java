package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2571 {

    public static int[][] map;

    public static int answer = 100;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(reader.readLine());
            map = new int[100][100];

            for (int n = 0; n < N; n++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                for (int x = a; x < a + 10; x++) {
                    for (int y = b; y < b + 10; y++) {
                        map[x][y]++;
                    }
                }
            }

            for (int sx = 0; sx < 100 - 1; sx++) {
                for (int sy = 0; sy < 100 - 1; sy++) {
                    if (map[sx][sy] == 0) {
                        continue;
                    }

                    for (int ex = sx + 1; ex < 100; ex++) {
                        for (int ey = sy + 1; ey < 100; ey++) {
                            if (map[ex][ey] == 0) {
                                break;
                            }

                            int now = (ex - sx + 1) * (ey - sy + 1);

                            if (now <= answer) {
                                continue;
                            }

                            if (check(sx, sy, ex, ey)) {
                                answer = now;
                            }
                        }
                    }
                }
            }

            writer.write(answer + "");
        }
    }

    static boolean check(int sx, int sy, int ex, int ey) {
        for (int x = sx; x <= ex; x++) {
            for (int y = sy; y <= ey; y++) {
                if (map[x][y] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}


