package boj.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj13023 {

    public static boolean[] check;

    public static boolean answer;

    public static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            check = new boolean[n];
            graph = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
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
            for (int i = 0; i < n; i++) {
                dfs(i, 1);
                if (answer) {
                    writer.write("1");
                    return;
                }
            }

            writer.write("0");
        }
    }

    public static void dfs(int node, int depth) {
        if (depth == 5) {
            answer = true;
            return;
        }
        check[node] = true;
        for (Integer link : graph.get(node)) {
            if (!check[link]) {
                dfs(link, depth + 1);
            }
            if (answer) {
                return;
            }
        }
        check[node] = false;
        // 재귀함소 호출 종료 시, 노드의 방문 상태를 false로 변경해준다
        // 이미 방문했지만, 다른 노드를 거쳐서 오는 경우에 depth 5를 만족할 수 있기 때문이다
        // dfs 문제이지만 무조건 탐색 했다고 끝나느 것이 아닌 문제이다
        // 예를들어 (0, 1) (1, 2) (0, 3) (3, 4) (4, 1) 다음과 같은 그래프가 있을 때
        // 0 -> 1 -> 2로 깊이 우선 탐색을 하는 경우 depth는 3이지만
        // 0 -> 3 -> 4 -> 1 -> 2로 깊이 우선 탐색을 하는 경우 depth가 5를 만족하게 된다
    }

}