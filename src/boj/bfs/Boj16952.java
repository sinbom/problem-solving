package boj.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16952 {

    public static int[][][] dir = {
            {
                    {-1, 0},
                    {1, 0},
                    {0, -1},
                    {0, 1}
            },
            {
                    {-1, -1},
                    {-1, 1},
                    {1, -1},
                    {1, 1}
            },
            {
                    {-1, -2},
                    {-2, -1},
                    {-2, 1},
                    {-1, 2},
                    {1, 2},
                    {2, 1},
                    {2, -1},
                    {1, -2}
            }
    };

    public static int n;

    public static int[][][][][] visited;

    public static int[][] map;

    public static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine().trim());
            map = new int[n][n];
            visited = new int[n][n][3][n * n][2];
            int r = 0;
            int c = 0;

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine().trim());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    if (map[i][j] == 1) {
                        r = i;
                        c = j;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < n * n; l++) {
                            for (int m = 0; m < 2; m++) {
                                visited[i][j][k][l][m] = -1;
                            }
                        }
                    }
                }
            }

            queue.offer(new int[]{r, c, 0, 0, 1, 0});
            queue.offer(new int[]{r, c, 1, 0, 1, 0});
            queue.offer(new int[]{r, c, 2, 0, 1, 0});
            visited[r][c][0][1][0] = 0;
            visited[r][c][1][1][0] = 0;
            visited[r][c][2][1][0] = 0;

            int[] result = bfs();

            writer.write(result[0] + " " + result[1]);
        }
    }

    public static int[] bfs() {
        Queue<int[]> priorityQueue = new PriorityQueue<>((l, r) -> {
            if (l[0] == r[0]) {
                return Integer.compare(l[1], r[1]);
            } else {
                return Integer.compare(l[0], r[0]);
            }
        });

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (map[current[0]][current[1]] == current[4] + 1) {
                if (map[current[0]][current[1]] == n * n) {
                    priorityQueue.offer(new int[]{current[3], current[5]});
                    continue;
                }
                current[4]++;
            }

            for (int i = 0; i < dir[current[2]].length; i++) {
                int j = 1;
                while (true) {
                    int nr = current[0] + dir[current[2]][i][0] * j;
                    int nc = current[1] + dir[current[2]][i][1] * j;
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                        if (visited[nr][nc][current[2]][current[4]][0] == -1 ||
                                visited[nr][nc][current[2]][current[4]][0] > current[3] + 1 ||
                                (visited[nr][nc][current[2]][current[4]][0] == current[3] + 1 &&
                                        visited[nr][nc][current[2]][current[4]][1] > current[5])) {
                            queue.offer(new int[]{nr, nc, current[2], current[3] + 1, current[4], current[5]});
                            visited[nr][nc][current[2]][current[4]][0] = current[3] + 1;
                            visited[nr][nc][current[2]][current[4]][1] = current[5];
                        }
                    } else {
                        break;
                    }
                    if (current[2] == 2) {
                        break;
                    }
                    j++;
                }
            }

            check(current, (current[2] + 1) % 3);
            check(current, (current[2] + 2) % 3);
        }

        return priorityQueue.peek();
    }

    public static void check(int[] cur, int nh) {
        if (visited[cur[0]][cur[1]][nh][cur[4]][0] == -1 || visited[cur[0]][cur[1]][nh][cur[4]][0] > cur[3] + 1 ||
                (visited[cur[0]][cur[1]][nh][cur[4]][0] == cur[3] + 1 && visited[cur[0]][cur[1]][nh][cur[4]][1] > cur[5] + 1)) {
            queue.offer(new int[]{cur[0], cur[1], nh, cur[3] + 1, cur[4], cur[5] + 1});
            visited[cur[0]][cur[1]][nh][cur[4]][0] = cur[3] + 1;
            visited[cur[0]][cur[1]][nh][cur[4]][1] = cur[5] + 1;
        }
    }

}
