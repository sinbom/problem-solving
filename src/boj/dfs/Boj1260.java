package boj.dfs;

import java.io.*;
import java.util.*;

public class Boj1260 {

    public static boolean[] check;

    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            check = new boolean[n + 1];
            List<List<Integer>> graph = new ArrayList<>(n + 1);

            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int s = Integer.parseInt(stringTokenizer.nextToken());
                int e = Integer.parseInt(stringTokenizer.nextToken());
                graph
                        .get(s)
                        .add(e);
                graph
                        .get(e)
                        .add(s);
            }

            for (int i = 1; i < n + 1; i++) {
                Collections.sort(graph.get(i));
            }

            dfs(graph, v);
            Arrays.fill(check, false);
            writer.newLine();
            bfs(graph, v);
        } finally {
            writer.close();
        }
    }

    public static void dfs(List<List<Integer>> graph, int node) throws IOException {
        check[node] = true;
        writer.write(node + " ");

        for (Integer link : graph.get(node)) {
            if (!check[link]) {
                dfs(graph, link);
            }
        }
    }

    public static void bfs(List<List<Integer>> graph, int node) throws IOException {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(node);
        check[node] = true;

        while (!queue.isEmpty()) {
            node = queue.pollFirst();
            writer.write(node + " ");
            for (Integer link : graph.get(node)) {
                if (!check[link]) {
                    check[link] = true;
                    queue.add(link);
                }
            }
        }
    }

}