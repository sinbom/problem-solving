package boj.implement;

import java.io.*;

public class Boj1913 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int t = Integer.parseInt(reader.readLine());
            int[][] map = new int[n][n];
            int value = n * n;
            int x = 0;
            int y = 0;
            int time = 0;
            int limit = n;

            while (value > 0) {
                x = time;

                for (int i = y; i < limit; i++) {
                    map[i][x] = value--;
                }

                y = limit - 1;

                for (int i = x + 1; i < limit; i++) {
                    map[y][i] = value--;
                }

                x = limit - 1;

                for (int i = y - 1; i >= time; i--) {
                    map[i][x] = value--;
                }

                y = time;

                for (int i = x - 1; i > time; i--) {
                    map[y][i] = value--;
                }

                time++;
                limit--;
                y = time;
            }

            StringBuilder sb = new StringBuilder();
            int tx = 0;
            int ty = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (t == map[i][j]) {
                        ty = i + 1;
                        tx = j + 1;
                    }
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            sb.append(ty).append(" ").append(tx);

            writer.write(sb.toString());
        }
    }

}
