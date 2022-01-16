package etc.graph;

import java.io.*;
import java.util.*;

/**
 * 이것이 코딩 테스트다 Chapter 10, 위상 정렬
 */
public class Etc2 {

    public static List<LinkedList<Integer>> graph;

    public static int[] count;

    public static int[] time;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int v = Integer.parseInt(reader.readLine());
            graph = new ArrayList<>(v + 1);
            count = new int[v + 1];
            time = new int[v + 1];

            for (int i = 0; i <= v; i++) {
                graph.add(new LinkedList<>());
            }

            for (int i = 1; i < v + 1; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                time[i] = Integer.parseInt(stringTokenizer.nextToken());

                while (stringTokenizer.hasMoreTokens()) {
                    int node = Integer.parseInt(stringTokenizer.nextToken());

                    if (node == -1) {
                        break;
                    }

                    graph
                            .get(node)
                            .add(i);
                    count[i] += 1;
                }
            }

            int[] result = topologySort();

            for (int i = 1; i < result.length; i++) {
                writer.write(result[i] + " ");
            }
        }
    }

    public static int[] topologySort() {
        int[] result = time.clone();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < count.length; i++) {
            if (count[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            for (Integer next : graph.get(current)) {
                result[next] = Math.max(result[next], result[current] + time[next]);
                count[next]--;

                if (count[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return result;
    }

}







