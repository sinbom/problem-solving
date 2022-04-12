package boj.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj11780 {

    public static int[][] map;

    public static int[][] path;

    public static int n;

    public static int INF = 10000001;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            Stack<Integer> stack = new Stack<>();
            path = new int[n + 1][n + 1];
            map = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    path[i][j] = INF;
                    if (i == j) {
                        continue;
                    }
                    map[i][j] = INF;
                }
            }

            for (int i = 0; i < m; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());
                int cost = Integer.parseInt(stringTokenizer.nextToken());

                map[start][end] = Math.min(map[start][end], cost);
                path[start][end] = start;
            }

            floydWarshall();

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] == INF) {
                        writer.write(0 + " ");
                    } else {
                        writer.write(map[i][j] + " ");
                    }
                }

                writer.newLine();
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (path[i][j] == INF) {
                        writer.write(0 + "\n");
                    } else {
                        int pre = j;
                        stack.push(j);

                        while (i != path[i][pre]) {
                            pre = path[i][pre];
                            stack.push(pre);
                        }

                        writer.write((stack.size() + 1) + " ");
                        writer.write(i + " ");

                        while (!stack.empty()) {
                            writer.write(stack.pop() + " ");
                        }

                        writer.newLine();
                    }
                }
            }
        }
    }

    public static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
    }

}



