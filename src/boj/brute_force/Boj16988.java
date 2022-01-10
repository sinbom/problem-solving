package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Boj16988 {

    public static int n;

    public static int m;

    public static int answer = 0;

    public static int[][] map;

    public static boolean[][] visit;

    public static int[] dx = {0, 0, 1, -1};

    public static int[] dy = {1, -1, 0, 0};

    public static List<Stone> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][m];
            visit = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());

                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());

                    if (map[i][j] == 0) {
                        list.add(new Stone(i, j));
                    }
                }
            }

            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    Stone a = list.get(i);
                    Stone b = list.get(j);
                    map[a.x][a.y] = 1;
                    map[b.x][b.y] = 1;
                    bfs();
                    map[a.x][a.y] = 0;
                    map[b.x][b.y] = 0;
                    visit = new boolean[n][m];
                }
            }

            writer.write(answer + "");
        }

    }

    private static void bfs() {
        Queue<Stone> queue = new LinkedList<>();
        int total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2 && !visit[i][j]) {
                    int nowCnt = 1;
                    int empty = 0;

                    visit[i][j] = true;
                    queue.offer(new Stone(i, j));

                    while (!queue.isEmpty()) {
                        Stone b = queue.poll();

                        for (int d = 0; d < 4; d++) {
                            int nx = b.x + dx[d];
                            int ny = b.y + dy[d];
                            if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny] || map[nx][ny] == 1) {
                                continue;
                            }

                            if (map[nx][ny] == 0) {
                                empty++;
                            }

                            if (map[nx][ny] == 2) {
                                visit[nx][ny] = true;
                                queue.offer(new Stone(nx, ny));
                                nowCnt++;
                            }
                        }
                    }

                    if (empty > 0) {
                        continue;
                    }

                    total += nowCnt;

                }
            }
        }

        if (total > answer) {
            answer = total;
        }
    }

    public static class Stone {

        private final int x;

        private final int y;

        public Stone(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }


}






