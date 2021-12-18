package boj.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15650 {

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            arr = new int[m];
            go(n, m, 1, 0);
        }
    }

    public static void go(int n, int m, int index, int selected) {
        if (selected == m) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + (i + 1 != arr.length ? " " : "\n"));
            }
        } else {
            if (index > n) {
                return;
            }
            arr[selected] = index;
            go(n, m, index + 1, selected + 1);
            arr[selected] = 0;
            go(n, m, index + 1, selected);
        }

    }

}