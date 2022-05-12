package boj.tree;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14438 {
    static int[] tree, arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            arr = new int[n + 1];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            tree = new int[n * 4];

            init(1, n, 1);
            int M = Integer.parseInt(reader.readLine());

            StringBuilder sb = new StringBuilder();
            while (M-- > 0) {
                stringTokenizer = new StringTokenizer(reader.readLine());

                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                int c = Integer.parseInt(stringTokenizer.nextToken());

                if (a == 1) {
                    update(1, n, 1, b, c);
                } else if (a == 2) {
                    sb.append(query(1, n, 1, b, c)).append("\n");
                }
            }

            writer.write(sb.toString());
        }
    }

    public static int init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
    }

    public static int query(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return Math.min(query(start, mid, node * 2, left, right), query(mid + 1, end, node * 2 + 1, left, right));
    }

    public static int update(int start, int end, int node, int idx, int val) {
        if (idx < start || idx > end) {
            return tree[node];
        }

        if (start == end) {
            return tree[node] = val;
        }

        int mid = (start + end) / 2;

        return tree[node] = Math.min(update(start, mid, node * 2, idx, val),
                update(mid + 1, end, node * 2 + 1, idx, val));
    }

}
