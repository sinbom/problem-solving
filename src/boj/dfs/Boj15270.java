package boj.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class Boj15270 {

    public static int answer = 0;

    public static boolean[] check;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[][] graph = new int[m][2];
            check = new boolean[n + 1];

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                graph[i][0] = Integer.parseInt(stringTokenizer.nextToken());
                graph[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            }

            dfs(graph, 0, 0);
            answer *= 2;

            if (answer < n) {
                answer++;
            }

            writer.write(answer + "");
        }
    }

    public static void dfs(int[][] graph, int index, int count) {
        if (index >= graph.length) {
            answer = Math.max(answer, count);
            return;
        }

        if (check[graph[index][0]] || check[graph[index][1]]) {
            dfs(graph, index + 1, count);
        } else {
            check[graph[index][0]] = true;
            check[graph[index][1]] = true;
            dfs(graph, index + 1, count + 1);
            check[graph[index][0]] = false;
            check[graph[index][1]] = false;
            dfs(graph, index + 1, count);
        }
    }


}