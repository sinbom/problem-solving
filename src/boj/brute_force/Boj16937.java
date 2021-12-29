package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16937 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int h = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            int n = Integer.parseInt(reader.readLine());
            int max = 0;
            int[][] sticker = new int[n][2];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                sticker[i][0] = Integer.parseInt(stringTokenizer.nextToken());
                sticker[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            }

            int a1;
            int a2;
            int b1;
            int b2;

            for (int i = 0; i < n - 1; i++) {
                a1 = sticker[i][0];
                b1 = sticker[i][1];

                for (int j = i + 1; j < n; j++) {
                    a2 = sticker[j][0];
                    b2 = sticker[j][1];

                    if (stick(a1, b1, h, w)) {
                        if (stick(a2, b2, h - a1, w) || stick(a2, b2, h, w - b1)) {
                            max = Math.max(max, a1 * b1 + a2 * b2);
                            continue;
                        }
                        if (stick(b2, a2, h - a1, w) || stick(b2, a2, h, w - b1)) {
                            max = Math.max(max, a1 * b1 + a2 * b2);
                            continue;
                        }
                    }

                    if (stick(b1, a1, h, w)) {
                        if (stick(a2, b2, h - b1, w) || stick(a2, b2, h, w - a1)) {
                            max = Math.max(max, a1 * b1 + a2 * b2);
                            continue;
                        }
                        if (stick(b2, a2, h - b1, w) || stick(b2, a2, h, w - a1)) {
                            max = Math.max(max, a1 * b1 + a2 * b2);
                            continue;
                        }
                    }
                }
            }

            writer.write(max + "");
        }
    }

    public static boolean stick(int a, int b, int H, int W) {
        return a <= H && b <= W;
    }

}