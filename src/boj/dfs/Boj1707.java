package boj.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1707 {

    public static List<List<Integer>> graph;

    public static int[] colors;

    public static boolean answer;

    public static int RED = 1;

    public static int BLACK = -1;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int k = Integer.parseInt(reader.readLine());

            for (int i = 0; i < k; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int n = Integer.parseInt(stringTokenizer.nextToken());
                int m = Integer.parseInt(stringTokenizer.nextToken());
                graph = new ArrayList<>(n + 1);
                colors = new int[n + 1];
                answer = true;

                for (int j = 0; j < n + 1; j++) {
                    graph.add(new ArrayList<>());
                }
                for (int j = 0; j < m; j++) {
                    stringTokenizer = new StringTokenizer(reader.readLine());
                    int u = Integer.parseInt(stringTokenizer.nextToken());
                    int v = Integer.parseInt(stringTokenizer.nextToken());
                    graph
                            .get(u)
                            .add(v);
                    graph
                            .get(v)
                            .add(u);
                }

                for (int j = 1; j < n + 1 && answer; j++) {
                    if (colors[j] == 0) {
                        dfs(j, RED);
                    }
                }

                writer.write(answer ? "YES\n" : "NO\n");
            }
        }
    }

    public static void dfs(int node, int color) {
        if (!answer) {
            return;
        }

        colors[node] = color;
        for (Integer link : graph.get(node)) {
            // 인정한 두 정점이 같은 색(플래그값)인 경우 이분 그래프가 아니다.
            if (colors[link] == color) {
                answer = false;
                return;
            }
            if (colors[link] == 0) {
                dfs(link, -color);
            }
        }
    }

}