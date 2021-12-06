package boj.dfs;

import java.io.*;
import java.util.*;

public class Boj16947 {

    public static List<LinkedList<Integer>> graph;

    public static boolean[] circle;

    public static boolean[] check;

    public static int[] answer;

    public static int n;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            graph = new ArrayList<>(n + 1);
            check = new boolean[n + 1];
            answer = new int[n + 1];
            circle = new boolean[n + 1];

            for (int i = 0; i <= n; i++) {
                graph.add(new LinkedList<>());
            }

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int left = Integer.parseInt(stringTokenizer.nextToken());
                int right = Integer.parseInt(stringTokenizer.nextToken());
                graph
                        .get(left)
                        .add(right);
                graph
                        .get(right)
                        .add(left);
            }

            dfs(1, 1);

            for (int i = 1; i <= n; i++) {
                if (!circle[i]) {
                    bfs(i, i);
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                stringBuilder.append(answer[i]).append(" ");
            }
            writer.write(stringBuilder.toString());
        }
    }

    public static boolean dfs(int cur, int prev) {
        if (check[cur]) {
            return true;
        }

        check[cur] = true;
        for (Integer next : graph.get(cur)) {
            if (next == prev) {
                continue;
            }

            if (dfs(next, cur)) {
                if (circle[next]) {
                    return false;
                }
                return circle[next] = true;
            }
        }

        return false;
    }

    public static void bfs(int c, int p) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{c, p, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cur = poll[0];
            int prev = poll[1];
            int weight = poll[2];

            if (circle[cur]) {
                answer[c] = weight;
                return;
            }

            for (Integer link : graph.get(cur)) {
                if (link != prev) {
                    queue.add(new int[]{link, cur, weight + 1});
                }
            }
        }
    }

}