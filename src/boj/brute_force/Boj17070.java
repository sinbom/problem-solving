package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj17070 {

    public static int n;

    public static int[][] map;

    public static int answer;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            DFS(1, 2, 0);
            writer.write(answer + "\n");
        }
    }

    public static void DFS(int x, int y, int direction) {
        if (x == n && y == n) {
            answer++;

            return;
        }

        if (direction == 0) {
            if (y + 1 <= n && map[x][y + 1] == 0) {
                DFS(x, y + 1, 0);
            }
        } else if (direction == 1) {
            if (x + 1 <= n && map[x + 1][y] == 0) {
                DFS(x + 1, y, 1);
            }
        } else if (direction == 2) {
            if (y + 1 <= n && map[x][y + 1] == 0) {
                DFS(x, y + 1, 0);
            }
            if (x + 1 <= n && map[x + 1][y] == 0) {
                DFS(x + 1, y, 1);
            }
        }

        if (y + 1 <= n && x + 1 <= n && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
            DFS(x + 1, y + 1, 2);
        }
    }

}




