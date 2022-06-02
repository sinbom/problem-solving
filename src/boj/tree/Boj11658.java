package boj.tree;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11658 {

    public static int n;

    public static int[][] arr;

    public static int[][] tree;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n + 1][n + 1];
            tree = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    update(i, j, arr[i][j]);
                }
            }

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int op = Integer.parseInt(stringTokenizer.nextToken());
                int x1 = Integer.parseInt(stringTokenizer.nextToken());
                int y1 = Integer.parseInt(stringTokenizer.nextToken());
                if (op == 1) {
                    int x2 = Integer.parseInt(stringTokenizer.nextToken());
                    int y2 = Integer.parseInt(stringTokenizer.nextToken());
                    stringBuilder
                            .append(sum(x2, y2) - sum(x2, y1 - 1) - sum(x1 - 1, y2) + sum(x1 - 1, y1 - 1))
                            .append("\n");
                } else {
                    int c = Integer.parseInt(stringTokenizer.nextToken());
                    update(x1, y1, c - arr[x1][y1]);
                    arr[x1][y1] = c;
                }
            }

            writer.write(stringBuilder.toString() + "\n");
        }

    }

    public static void update(int x, int y, int val) {
        while (x <= n) {
            for (int i = y; i <= n; ) {
                tree[x][i] += val;
                i += i & -i;
            }
            x += x & -x;
        }
    }

    public static int sum(int x, int y) {
        int result = 0;

        while (x > 0) {
            for (int i = y; i > 0; ) {
                result += tree[x][i];
                i -= i & -i;
            }
            x -= x & -x;
        }

        return result;
    }

}