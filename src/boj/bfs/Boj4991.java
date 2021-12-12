package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj4991 {

    public static int n;

    public static int m;

    public static char[][] map;

    public static int[][] graph;

    public static boolean[] check;

    public static List<int[]> dirties;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            while (true) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                m = Integer.parseInt(stringTokenizer.nextToken());
                n = Integer.parseInt(stringTokenizer.nextToken());

                if (n == 0 && m == 0) {
                    return;
                }

                map = new char[n][m];
                dirties = new ArrayList<>(11);
                answer = Integer.MAX_VALUE;

                for (int i = 0; i < n; i++) {
                    String s = reader.readLine();
                    for (int j = 0; j < m; j++) {
                        map[i][j] = s.charAt(j);
                        if (map[i][j] == 'o') {
                            dirties.add(0, new int[]{i, j});
                        } else if (map[i][j] == '*') {
                            dirties.add(new int[]{i, j});
                        }
                    }
                }

                solve();
                writer.write(answer + "\n");
            }
        }
    }

    public static void solve() {
        check = new boolean[dirties.size()];
        graph = new int[dirties.size()][dirties.size()];

        for (int i = 0; i < dirties.size(); i++) {
            for (int j = i + 1; j < dirties.size(); j++) {
                int[] dust1 = dirties.get(i);
                int[] dust2 = dirties.get(j);
                int distance = bfs(dust1[0], dust1[1], dust2[0], dust2[1]);

                if (distance == -1) {
                    answer = -1;
                    return;
                }

                graph[i][j] = graph[j][i] = distance;
            }
        }

        brute(0, 0, 0);
    }

    public static void brute(int idx, int cnt, int sum) {
        if (cnt == dirties.size() - 1) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 1; i < dirties.size(); i++) {
            if (check[i]) {
                continue;
            }
            check[i] = true;
            brute(i, cnt + 1, sum + graph[idx][i]);
            check[i] = false;
        }
    }

    public static int bfs(int sx, int sy, int ex, int ey) {
        int distance = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] check = new boolean[n][m];

        check[sx][sy] = true;
        queue.add(new int[]{sx, sy});

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] poll = queue.poll();

                if (poll[0] == ex && poll[1] == ey) {
                    return distance;
                }

                for (int i = 0; i < dx.length; i++) {
                    int nx = poll[0] + dx[i];
                    int ny = poll[1] + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        continue;
                    }

                    if (map[nx][ny] == 'x' || check[nx][ny]) {
                        continue;
                    }

                    check[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
            distance++;
        }

        return -1;
    }

}