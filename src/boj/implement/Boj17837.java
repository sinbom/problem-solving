package boj.implement;

import java.io.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj17837 {

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
            LinkedList<Integer>[][] map = new LinkedList[n][n];
            int[][] horses = new int[k + 1][3];

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
                map[x][y].add(i);
                horses[i][0] = x;
                horses[i][1] = y;
                horses[i][2] = d;
            }

            for (int t = 1; t <= 1000; t++) {
                for (int i = 1; i <= k; i++) {
                    int x = horses[i][0];
                    int y = horses[i][1];
                    int d = horses[i][2];
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || color[nx][ny] == BLUE) {
                        horses[i][2] = reverseDirection(d);
                        d = horses[i][2];
                        nx = x + dx[d];
                        ny = y + dy[d];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= n || color[nx][ny] == BLUE) {
                            continue;
                        }
                    }

                    if (color[nx][ny] == RED) {
                        while (map[x][y].size() > 0) {
                            Integer temp = map[x][y].removeLast();
                            horses[temp][0] = nx;
                            horses[temp][1] = ny;
                            map[nx][ny].add(temp);

                            if (temp == i) {
                                break;
                            }
                        }
                    } else {
                        Stack<Integer> stack = new Stack<>();
                        while (map[x][y].size() > 0) {
                            Integer temp = map[x][y].removeLast();
                            horses[temp][0] = nx;
                            horses[temp][1] = ny;
                            stack.push(temp);

                            if (temp == i) {
                                break;
                            }
                        }
                        while (!stack.isEmpty()) {
                            map[nx][ny].add(stack.pop());
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

    public static int reverseDirection(int direction) {
        return direction % 2 == 0 ? direction - 1 : direction + 1;
    }

}