package boj.brute_force;

import java.io.*;

public class Boj10974 {

    public static boolean[] check;

    public static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(reader.readLine());
            int[] a = new int[n];
            check = new boolean[n];
            all(a, n, 0);
            bufferedWriter.close();
        }
    }

    public static void all(int[] a, int n, int selected) throws IOException {
        if (n == selected) {
            for (int i = 0; i < a.length; i++) {
                bufferedWriter.write(a[i] + " ");
            }
            bufferedWriter.newLine();
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (check[i - 1]) {
                continue;
            }
            check[i - 1] = true;
            a[selected] = i;
            all(a, n, selected + 1);
            check[i - 1] = false;
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