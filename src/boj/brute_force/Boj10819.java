package boj.brute_force;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj10819 {

    public static int[] a;

    public static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(reader.readLine());
            a = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(tokenizer.nextToken());
            }
            Arrays.sort(a);
            do {
                int sum = 0;
                for (int i = 0; i < a.length - 1; i++) {
                    sum += Math.abs(a[i] - a[i + 1]);
                }
                if (max < sum) {
                    max = sum;
                }
            } while (go());
            writer.write(max + "");
        }
    }

    public static boolean go() {
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