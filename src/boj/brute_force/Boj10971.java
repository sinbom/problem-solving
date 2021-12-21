package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj10971 {

    public static int[] a;

    public static int[][] w;

    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(reader.readLine());
            a = new int[n];
            w = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    w[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
                a[i] = i;
            }

            do {
                int sum = 0;
                boolean ok = true;
                for (int i = 0; i < a.length; i++) {
                    int distance = w[a[i % a.length]][a[(i + 1) % a.length]];
                    if (distance == 0) {
                        ok = false;
                        break;
                    }
                    sum += distance;
                }
                if (ok) {
                    if (min > sum) {
                        min = sum;
                    }
                }
            } while (go());

            writer.write(min + "");
        }
    }

    public static boolean go() {
        int i = a.length - 1;
        while (i > 1 && a[i - 1] >= a[i]) {
            i--;
        }
        if (i <= 1) {
            return false;
        }
        int j = a.length - 1;
        while (a[i - 1] >= a[j]) {
            j--;
        }
        swap(a, i - 1, j);
        j = a.length - 1;
        while (i < j) {
            swap(a, i++, j--);
        }
        return true;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}