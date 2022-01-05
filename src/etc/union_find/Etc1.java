package etc.union_find;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 Chapter 10, 서로조 집합
 */
public class Etc1 {

    public static void main(    String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] arr = new int[n + 1];
            int[] weight = new int[n + 1];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
                weight[i] = 1;
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int op = Integer.parseInt(stringTokenizer.nextToken());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                if (op == 0) {
                    union(arr, weight, a, b);
                } else {
                    if (find(arr, a) == find(arr, b)) {
                        writer.write("YES\n");
                    } else {
                        writer.write("NO\n");
                    }
                }
            }
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

}




