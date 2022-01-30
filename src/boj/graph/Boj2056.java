package boj.graph;

import java.io.*;
import java.util.*;

public class Boj2056 {

    public static int n;

    public static List<List<Integer>> graph = new ArrayList<>();

    public static int[] time;

    public static int[] indegree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            indegree = new int[n + 1];
            time = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 1; i <= n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                time[i] = Integer.parseInt(stringTokenizer.nextToken());
                int num = Integer.parseInt(stringTokenizer.nextToken());

                for (int j = 0; j < num; j++) {
                    int node = Integer.parseInt(stringTokenizer.nextToken());
                    graph
                            .get(node)
                            .add(i);
                    indegree[i]++;
                }
            }

            writer.write(topologicalSort() + "");
        }
    }

    public static int topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = time[i];

            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph.get(current)) {
                indegree[next]--;

                result[next] = Math.max(result[next], result[current] + time[next]);

                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, result[i]);
        }

        return answer;
    }
}