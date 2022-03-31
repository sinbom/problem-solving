package boj.graph;

import java.io.*;
import java.util.*;

public class Boj1766 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] indegree = new int[n + 1];
            List<ArrayList<Integer>> a = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                a.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int first = Integer.parseInt(stringTokenizer.nextToken());
                int last = Integer.parseInt(stringTokenizer.nextToken());

                a.get(first).add(last);
                indegree[last]++;
            }

            Queue<Integer> pq = new PriorityQueue<>();
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) {
                    pq.offer(i);
                }
            }

            while (!pq.isEmpty()) {
                int now = pq.poll();
                sb.append(now + " ");

                for (int next : a.get(now)) {
                    indegree[next]--;

                    if (indegree[next] == 0) {
                        pq.offer(next);
                    }
                }
            }

            writer.write(sb.toString());
        }
    }
}



