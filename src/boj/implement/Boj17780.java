package boj.implement;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj17780 {

    public static final int RED = 1;

    public static final int BLUE = 2;

    public static int[] dx = {0, 0, 0, -1, 1};

    public static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int[][] color = new int[n][n];
            LinkedList<Horse>[][] map = new LinkedList[n][n];
            int[][] position = new int[k + 1][2];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    color[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    map[i][j] = new LinkedList<>();
                }
            }

            for (int i = 1; i <= k; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int d = Integer.parseInt(stringTokenizer.nextToken());
                map[x][y].add(new Horse(i, d));
                position[i][0] = x;
                position[i][1] = y;
            }

            for (int t = 1; t <= 1000; t++) {
                for (int i = 1; i <= k; i++) {
                    int x = position[i][0];
                    int y = position[i][1];
                    Horse horse = map[x][y].getFirst();

                    if (horse.number != i) {
                        continue;
                    }

                    int nx = x + dx[horse.direction];
                    int ny = y + dy[horse.direction];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || color[nx][ny] == BLUE) {
                        horse.reverseDirection();
                        nx = x + dx[horse.direction];
                        ny = y + dy[horse.direction];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= n || color[nx][ny] == BLUE) {
                            continue;
                        }
                    }

                    if (color[nx][ny] == RED) {
                        while (map[x][y].size() > 0) {
                            Horse temp = map[x][y].removeLast();
                            position[temp.number][0] = nx;
                            position[temp.number][1] = ny;
                            map[nx][ny].add(temp);
                        }
                    } else {
                        while (map[x][y].size() > 0) {
                            Horse temp = map[x][y].removeFirst();
                            position[temp.number][0] = nx;
                            position[temp.number][1] = ny;
                            map[nx][ny].add(temp);
                        }
                    }

                    if (map[nx][ny].size() >= 4) {
                        writer.write(t + "");
                        return;
                    }
                }
            }

            writer.write("-1");
        }
    }

    public static class Horse {

        private final int number;
        private int direction;

        public Horse(int number, int direction) {
            this.number = number;
            this.direction = direction;
        }

        public void reverseDirection() {
            direction = direction % 2 == 0 ? direction - 1 : direction + 1;
        }

    }

}