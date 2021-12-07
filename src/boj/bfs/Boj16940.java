package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj16940 {

    public static List<LinkedList<Integer>> graph;

    public static boolean[] check;

    public static int[] answer;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            graph = new ArrayList<>(n + 1);
            check = new boolean[n + 1];
            answer = new int[n];

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
            }

            if (answer[0] != 1) {
                writer.write("0");
                return;
            }

            if (bfs(1)) {
                writer.write("1");
            } else {
                writer.write("0");
            }
        }
    }

    public static boolean bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int idx = 1;
        queue.add(node);
        check[node] = true;

        while (!queue.isEmpty()) {
            set.clear();
            Integer cur = queue.poll();

            for (Integer next : graph.get(cur)) {
                if (!check[next]) {
                    set.add(next);
                    check[next] = true;
                }
            }

            int size = set.size();

            for (int i = idx; i < idx + size; i++) {
                if (set.contains(answer[i])) {
                    queue.add(answer[i]);
                } else {
                    return false;
                }
            }

            idx += size;
        }

        return true;
    }

}