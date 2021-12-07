package boj.dfs;

import java.io.*;
import java.util.Arrays;

public class Boj12946 {

    public static char[][] map;

    public static int[][] col;

    public static int n;

    public static int answer = 0;

    public static int[] dx = {-1, -1, 0, 1, 1, 0};

    public static int[] dy = {0, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            map = new char[n][n];
            col = new int[n][n];

            for (int[] ints : col) {
                Arrays.fill(ints, -1);
            }

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < s.length(); j++) {
                    map[i][j] = s.charAt(j);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 'X' && col[i][j] == -1) {
                        dfs(i, j, 0);
                    }
                }
            }

            writer.write(answer + "");
        }
    }

    public static void dfs(int x, int y, int c) {
        col[x][y] = c; // 방문 처리를 0 또는 1로하여 이분 그래프 여부를 확인
        answer = Math.max(answer, 1); // 방문해야 하는 노드가 하나라도 있다면 1이므로

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == '-') {
                continue;
            }

            if (col[nx][ny] == -1) {
                dfs(nx, ny, 1 - c);
            }

            answer = Math.max(answer, 2); // 이분 그래프가 아닌 경우

            if (col[nx][ny] == c) { // 이분 그래프라면, 육각 모양 판은 3개의 색으로 표현 가능
                answer = Math.max(answer, 3);
            }

        }
    }


}