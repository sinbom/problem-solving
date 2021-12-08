package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj16946 {

    public static int[][] map;

    public static int[][] area;

    public static List<Integer> areaSize;

    public static boolean[][] check;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int n;

    public static int m;


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][m];
            area = new int[n][m];
            areaSize = new ArrayList<>(n * m / 2 + 2);
            areaSize.add(0);

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = s.charAt(j) - '0';
                }
            }

            int areaIndex = 1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (area[i][j] == 0 && map[i][j] == 0) {
                        int count = bfs(i, j, areaIndex);
                        areaSize.add(count);
                        areaIndex++;
                    }
                }
            }

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (area[i][j] == 0) {
                        stringBuilder.append(count(i, j));
                    } else {
                        stringBuilder.append("0");
                    }
                }
                stringBuilder.append("\n");
            }

            writer.write(stringBuilder.toString());
        }
    }

    public static int count(int x, int y) {
        int count = 1;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || area[nx][ny] == 0) {
                continue;
            }

            if (!set.contains(area[nx][ny])) {
                set.add(area[nx][ny]);
                count += areaSize.get(area[nx][ny]);
            }
        }

        return count % 10;
    }

    public static int bfs(int x, int y, int areaIndex) {
        int count = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        area[x][y] = areaIndex;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] != 0) {
                    continue;
                }

                if (area[nx][ny] == 0) {
                    area[nx][ny] = areaIndex;
                    count++;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return count;
    }


}