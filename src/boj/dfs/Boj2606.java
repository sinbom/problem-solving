package boj.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2606 {

    public static int answer = 0;

    public static boolean[] check;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int c = Integer.parseInt(reader.readLine());
            List<LinkedList<Integer>> graph = new ArrayList<>(n + 1);
            check = new boolean[n + 1];

            for (int i = 0; i < n + 1; i++) {
                graph.add(new LinkedList<>());
            }

            for (int i = 0; i < c; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int node = Integer.parseInt(stringTokenizer.nextToken());
                int link = Integer.parseInt(stringTokenizer.nextToken());
                graph
                        .get(node)
                        .add(link);
                graph
                        .get(link)
                        .add(node);
            }

            dfs(graph, 1);

            writer.write(answer + "");
        }
    }

    public static void dfs(List<LinkedList<Integer>> graph, int node) {
        check[node] = true;

        for (Integer link : graph.get(node)) {
            if (!check[link]) {
                answer++;
                dfs(graph, link);
            }
        }
    }


}