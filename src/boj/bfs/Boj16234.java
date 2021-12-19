package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj16234 {

    public static int n;

    public static int l;

    public static int r;

    public static int[][] map;

    public static boolean[][] check;

    public static int[] dx = {-1, 0, 1, 0};

    public static int[] dy = {0, 1, 0, -1};

    public static int groupSum = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            l = Integer.parseInt(stringTokenizer.nextToken());
            r = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][n];
            check = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int answer = 0;

            while (true) {
                boolean isMoved = false;

                for (int i = 0; i < n; i++) {
                    Arrays.fill(check[i], false);
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!check[i][j]) {
                            List<Node> bfs = bfs(new Node(i, j));

                            if (bfs.size() == 1) {
                                continue;
                            }

                            if (isEqualsWithSum(bfs)) {
                                continue;
                            }

                            isMoved = true;
                        }
                    }
                }

                if (!isMoved) {
                    break;
                }

                answer++;
            }

            writer.write(answer + "");
        }
    }

    public static boolean isEqualsWithSum(List<Node> nodes) {
        boolean isAllEquals = true;
        int avg = groupSum / nodes.size();

        for (Node node : nodes) {
            if (map[node.x][node.y] != groupSum) {
                isAllEquals = false;
            }
            map[node.x][node.y] = avg;
        }

        return isAllEquals;
    }

    public static List<Node> bfs(Node node) {
        List<Node> positions = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        int sum = 0;

        positions.add(node);
        queue.add(node);
        check[node.x][node.y] = true;
        sum += map[node.x][node.y];

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int x = current.x;
            int y = current.y;

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isOutSide(nx, ny) || check[nx][ny]) {
                    continue;
                }

                if (!canMove(map[x][y], map[nx][ny])) {
                    continue;
                }

                check[nx][ny] = true;
                sum += map[nx][ny];
                Node next = new Node(nx, ny);
                queue.add(next);
                positions.add(next);
            }
        }
        groupSum = sum;

        return positions;
    }

    public static boolean canMove(int src, int target) {
        int diff = Math.abs(src - target);

        return diff >= l && diff <= r;
    }

    public static boolean isOutSide(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
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