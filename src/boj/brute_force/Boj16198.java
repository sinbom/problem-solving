package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16198 {

    public static int[] arr;

    public static boolean[] check;

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            arr = new int[n];
            check = new boolean[n];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            go(0);

            writer.write(answer + "");
        }
    }

    public static void go(int sum) {
        for (int i = 0; i < arr.length; i++) {
            if (!check[i]) {
                int j = i - 1;
                int k = i + 1;

                while (j >= 0) {
                    if (!check[j]) {
                        break;
                    }
                    j--;
                }
                while (k < arr.length) {
                    if (!check[k]) {
                        break;
                    }
                    k++;
                }

                if (j >= 0 && k < arr.length) {
                    check[i] = true;
                    go(sum + (arr[j] * arr[k]));
                    check[i] = false;
                } else {
                    answer = Math.max(answer, sum);
                }
            }
        }
    }

}