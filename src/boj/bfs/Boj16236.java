package boj.bfs;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16236 {

    public static int n;

    public static int[][] map;

    public static boolean[][] check;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int eatX = Integer.MAX_VALUE;

    public static int eatY = Integer.MAX_VALUE;

    public static int dist = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            map = new int[n][n];
            check = new boolean[n][n];

            int sharkX = 0;
            int sharkY = 0;
            int size = 2;
            int count = 0;
            int answer = 0;

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    if (map[i][j] == 9) {
                        sharkX = i;
                        sharkY = j;
                        map[i][j] = 0;
                    }
                }
            }

            while (true) {
                init();
                bfs(sharkX, sharkY, size);

                if (dist == Integer.MAX_VALUE) {
                    break;
                }

                answer += dist;
                map[eatX][eatY] = 0;
                sharkX = eatX;
                sharkY = eatY;
                count++;

                if (count == size) {
                    size++;
                    count = 0;
                }
            }

            writer.write(answer + "");
        }
    }

    public static void init() {
        eatX = Integer.MAX_VALUE;
        eatY = Integer.MAX_VALUE;
        dist = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            Arrays.fill(check[i], false);
        }
    }

    public static void bfs(int x, int y, int size) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});
        check[x][y] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            x = poll[0];
            y = poll[1];

            if (map[x][y] != 0 && size > map[x][y]) {
                if (dist > poll[2]) {
                    eatX = x;
                    eatY = y;
                    dist = poll[2];
                } else if (dist == poll[2]) {
                    if (eatX > x) {
                        eatX = x;
                        eatY = y;
                    } else if (eatX == x) {
                        if (eatY > y) {
                            eatY = y;
                        }
                    }
                }
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nw = poll[2] + 1;

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                if (check[nx][ny] || size < map[nx][ny]) {
                    continue;
                }

                check[nx][ny] = true;
                queue.add(new int[]{nx, ny, nw});
            }
        }
    }

}