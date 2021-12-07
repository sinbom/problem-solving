package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj16964 {

    public static List<LinkedList<Integer>> graph;

    public static boolean[] check;

    public static int[] answer;

    public static int[] order;

    public static int index = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            graph = new ArrayList<>(n + 1);
            check = new boolean[n + 1];
            answer = new int[n];
            order = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                graph.add(new LinkedList<>());
            }

            for (int i = 0; i < n - 1; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(stringTokenizer.nextToken());
                int to = Integer.parseInt(stringTokenizer.nextToken());
                graph
                        .get(from)
                        .add(to);
                graph
                        .get(to)
                        .add(from);
            }

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                answer[i] = Integer.parseInt(stringTokenizer.nextToken());
                order[answer[i]] = i + 1;
            }

            if (answer[0] != 1) {
                writer.write("0");
                return;
            }

            for (int i = 1; i <= n; i++) {
                graph
                        .get(i)
                        .sort(Comparator.comparingInt(o -> order[o]));
            }

            dfs(1);

            for (int i = 0; i < n; i++) {
                if (answer[i] != order[i]) {
                    writer.write("0");
                    return;
                }
            }

            writer.write("1");
        }
    }

    public static void dfs(int cur) {
        check[cur] = true;
        order[index++] = cur;

        for (Integer next : graph.get(cur)) {
            if (!check[next]) {
                dfs(next);
            }
        }
    }

}