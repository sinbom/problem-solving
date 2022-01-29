package boj.graph;

import java.io.*;
import java.util.*;

public class Boj2252 {

    public static void main(String[] args) throws IOException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] count = new int[n + 1];
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i <= n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int s = Integer.parseInt(stringTokenizer.nextToken());
                int e = Integer.parseInt(stringTokenizer.nextToken());

                graph
                        .get(s)
                        .add(e);
                count[e]++;
            }

            Queue<Integer> queue = new LinkedList<>();

            for (int i = 1; i < count.length; i++) {
                if (count[i] == 0) {
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (Integer next : graph.get(current)) {
                    count[next]--;
                    if (count[next] == 0) {
                        queue.offer(next);
                    }
                }

                writer.write(current + " ");
            }
        }
    }
}