package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj10972 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(reader.readLine());
            int[] a = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(tokenizer.nextToken());
            }
            if (go(a)) {
                for (int i = 0; i < a.length; i++) {
                    System.out.print(a[i] + " ");
                }
            } else {
                System.out.print(-1);
            }
        }
    }

    public static boolean go(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }
        if (i <= 0) {
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