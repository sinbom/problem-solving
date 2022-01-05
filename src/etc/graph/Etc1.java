package etc.graph;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 Chapter 10, 최소 신장 트리
 */
public class Etc1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] arr = new int[n + 1];
            int[] weight = new int[n + 1];
            Node[] nodes = new Node[m];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
                weight[i] = 1;
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                int c = Integer.parseInt(stringTokenizer.nextToken());

                nodes[i] = new Node(a, b, c);
            }

            Arrays.sort(nodes);

            int result = 0;
            int max = 0;

            for (Node node : nodes) {
                if (find(arr, node.a) != find(arr, node.b)) {
                    union(arr, weight, node.a, node.b);
                    result += node.c;
                    max = node.c;
                }
            }

            writer.write(result - max + "");
        }
    }

    public static int find(int[] arr, int node) {
        if (arr[node] != node) {
            arr[node] = find(arr, arr[node]);
        }

        return arr[node];
    }

    public static void union(int[] arr, int[] weight, int a, int b) {
        int aRoot = find(arr, a);
        int bRoot = find(arr, b);

        if (aRoot == bRoot) {
            return;
        }

        if (weight[aRoot] > weight[bRoot]) {
            arr[bRoot] = aRoot;
            weight[aRoot] += weight[bRoot];
        } else {
            arr[aRoot] = bRoot;
            weight[bRoot] += weight[aRoot];
        }
    }

    public static class Node implements Comparable<Node> {

        private final int a;
        private final int b;
        private final int c;

        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }

    }

}




