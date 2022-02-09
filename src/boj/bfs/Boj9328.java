package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj9328 {

    public static char[][] map;

    public static boolean[] alpha;

    public static List<List<Node>> gates;

    public static boolean[][] visited;

    public static int h;

    public static int w;

    public static int[] dx = {-1, 1, 0, 0};

    public static int[] dy = {0, 0, -1, 1};

    public static int count;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int tc = Integer.parseInt(reader.readLine());

            while (tc-- > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                h = Integer.parseInt(stringTokenizer.nextToken());
                w = Integer.parseInt(stringTokenizer.nextToken());
                map = new char[h + 2][w + 2];
                visited = new boolean[h + 2][w + 2];
                alpha = new boolean[26];
                gates = new ArrayList<>();
                count = 0;

                for (int i = 0; i < 26; i++) {
                    gates.add(new ArrayList<>());
                }

                for (int i = 0; i < h + 2; i++) {
                    for (int j = 0; j < w + 2; j++) {
                        map[i][j] = '.';
                    }
                }

                for (int i = 1; i <= h; i++) {
                    String readLine = reader.readLine();
                    for (int j = 1; j <= w; j++) {
                        map[i][j] = readLine.charAt(j - 1);
                    }
                }

                String readLine = reader.readLine();
                if (!readLine.equals("0")) {
                    for (int i = 0; i < readLine.length(); i++) {
                        int temp = readLine.charAt(i) - 'a';
                        alpha[temp] = true;
                    }
                }

                bfs();

                writer.write(count + "\n");
            }
        }
    }

    public static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h + 2 || ny >= w + 2) {
                    continue;
                }

                if (map[nx][ny] == '*' || visited[nx][ny]) {
                    continue;
                }

                int elem = map[nx][ny];
                if (elem - 'A' >= 0 && elem - 'A' <= 25) {
                    if (alpha[elem - 'A']) {
                        map[nx][ny] = '.';
                        visited[nx][ny] = true;
                        queue.add(new Node(nx, ny));
                    } else {
                        gates.get(elem - 'A').add(new Node(nx, ny));
                    }
                } else if (elem - 'a' >= 0 && elem - 'a' <= 25) {
                    alpha[elem - 'a'] = true;
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny));

                    for (int j = 0; j <= 25; j++) {
                        if (gates.get(j).size() != 0 && alpha[j]) {
                            for (int z = 0; z < gates.get(j).size(); z++) {
                                Node temp = gates.get(j).get(z);
                                map[temp.x][temp.y] = '.';
                                visited[temp.x][temp.y] = true;
                                queue.add(new Node(temp.x, temp.y));
                            }
                        }
                    }
                } else if (elem == '$') {
                    count++;
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                } else {
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }

            }
        }
    }

    public static class Node {

        private final int x;

        private final int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}