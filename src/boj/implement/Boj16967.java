package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16967 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int h = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            int[][] b = new int[h][w];
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < h; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < w; j++) {
                    b[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    if (i >= x && j >= y) {
                        b[i][j] = b[i][j] - b[i - x][j - y];
                    }
                    stringBuilder.append(b[i][j]).append(' ');
                }
                stringBuilder.append('\n');
            }

            writer.write(stringBuilder.toString());
        }
    }
}