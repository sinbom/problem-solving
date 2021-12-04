package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16197 {

    public static char[][] arr;

    public static boolean[][][][] check;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            arr = new char[n][m];
            check = new boolean[n][m][n][m];
            int[][] positions = new int[2][2];
            int index = 0;

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    arr[i][j] = s.charAt(j);
                    if (arr[i][j] == 'o') {
                        positions[index][0] = i;
                        positions[index][1] = j;
                        index++;
                    }
                }
            }

            writer.write(bfs(positions) + "");
        }
    }

    public static int bfs(int[][] positions) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{positions[0][0], positions[0][1], positions[1][0], positions[1][1], 0});
        check[positions[0][0]][positions[0][1]][positions[1][0]][positions[1][1]] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[4] >= 10) {
                break;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx1 = poll[0] + dx[i];
                int ny1 = poll[1] + dy[i];
                int nx2 = poll[2] + dx[i];
                int ny2 = poll[3] + dy[i];
                int count = 0;

                if (nx1 >= 0 && ny1 >= 0 && nx1 < arr.length && ny1 < arr[0].length && arr[nx1][ny1] == '#') {
                    nx1 = poll[0];
                    ny1 = poll[1];
                }

                if (nx2 >= 0 && ny2 >= 0 && nx2 < arr.length && ny2 < arr[0].length && arr[nx2][ny2] == '#') {
                    nx2 = poll[2];
                    ny2 = poll[3];
                }

                if (nx1 < 0 || ny1 < 0 || nx1 >= arr.length || ny1 >= arr[0].length) {
                    count++;
                }
                if (nx2 < 0 || ny2 < 0 || nx2 >= arr.length || ny2 >= arr[0].length) {
                    count++;
                }

                if (count == 1) {
                    return poll[4] + 1;
                }

                if (count == 0 && !check[nx1][ny1][nx2][ny2]) {
                    check[nx1][ny1][nx2][ny2] = true;
                    queue.offer(new int[]{nx1, ny1, nx2, ny2, poll[4] + 1});
                }
            }
        }

        return -1;
    }

}