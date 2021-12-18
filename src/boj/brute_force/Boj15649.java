package boj.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15649 {

    public static boolean[] check;

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            check = new boolean[n];
            arr = new int[m];
            go(n, m, 0);
        }
    }

    public static void go(int n, int m, int depth) {
        if (depth == m) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + (j + 1 != arr.length ? " " : "\n"));
            }
        } else {
            for (int i = 1; i <= n; i++) {
                if (check[i - 1]) {
                    continue;
                }
                check[i - 1] = true;
                arr[depth] = i;
                go(n, m, depth + 1);
                check[i - 1] = false;
            }
        }
    }

}