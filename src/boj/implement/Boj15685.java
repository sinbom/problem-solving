package boj.implement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15685 {

    public static int[] dx = {0, -1, 0, 1};

    public static int[] dy = {1, 0, -1, 0};

    public static boolean[][] map;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            map = new boolean[101][101];
            int answer = 0;

            while (n-- > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int y = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int d = Integer.parseInt(stringTokenizer.nextToken());
                int g = Integer.parseInt(stringTokenizer.nextToken());
                List<Integer> list = new ArrayList<>(1 << g);
                map[x][y] = true;
                x = x + dx[d];
                y = y + dy[d];
                map[x][y] = true;
                list.add(d);

                for (int i = 0; i < g; i++) {
                    for (int j = list.size() - 1; j >= 0; j--) {
                        Integer direction = (list.get(j) + 1) % dx.length;

                        x = x + dx[direction];
                        y = y + dy[direction];
                        map[x][y] = true;
                        list.add(direction);
                    }
                }
            }

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                        answer++;
                    }
                }
            }

            writer.write(answer + "");
        }
    }

}