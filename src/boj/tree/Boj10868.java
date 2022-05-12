package boj.tree;

import java.io.*;
import java.util.StringTokenizer;

public class Boj10868 {
    static int[] arr, minTree;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(reader.readLine());
            }

            minTree = new int[n * 4];

            minInit(1, n, 1);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());

                int left = Integer.parseInt(stringTokenizer.nextToken());
                int right = Integer.parseInt(stringTokenizer.nextToken());

                stringBuilder.append(minFind(1, n, 1, left, right)).append("\n");
            }

            writer.write(stringBuilder.toString());
        }
    }

    public static int minInit(int start, int end, int node) {
        if (start == end) {
            return minTree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(minInit(start, mid, node * 2), minInit(mid + 1, end, node * 2 + 1));
    }

    public static int minFind(int start, int end, int node, int left, int right) {
        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }

        if (left <= start && end <= right) {
            return minTree[node];
        }

        int mid = (start + end) / 2;

        return Math.min(minFind(start, mid, node * 2, left, right), minFind(mid + 1, end, node * 2 + 1, left, right));
    }

}
