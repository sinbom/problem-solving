package boj.tree;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2042 {

    public static long[] arr;

    public static long[] tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            arr = new long[n + 1];
            tree = new long[n * 4];

            for (int i = 1; i <= n; i++) {
                arr[i] = Long.parseLong(reader.readLine());
            }


            init(1, n, 1);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < m + k; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());

                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                long c = Long.parseLong(stringTokenizer.nextToken());

                if (a == 1) {
                    long dif = c - arr[b];
                    arr[b] = c;
                    update(1, n, 1, b, dif);
                } else if (a == 2) {
                    stringBuilder
                            .append(sum(1, n, 1, b, (int) c))
                            .append("\n");
                }
            }

            writer.write(stringBuilder.toString());
        }
    }

    public static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    public static long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    public static void update(int start, int end, int node, int idx, long dif) {
        if (idx < start || idx > end) {
            return;
        }

        tree[node] += dif;
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, dif);
        update(mid + 1, end, node * 2 + 1, idx, dif);
    }

}