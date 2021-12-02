package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14225 {

    public static int[] arr;

    public static boolean[] check;

    public static int n;

    public static int s;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n];
            stringTokenizer = new StringTokenizer(reader.readLine());
            int max = 0;

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
                max += arr[i];
            }

            check = new boolean[max + 1];

            go(0, 0);

            int answer = max + 1;

            for (int i = 1; i < check.length; i++) {
                if (!check[i]) {
                    answer = i;
                    break;
                }
            }

            writer.write(answer + "");
        }
    }

    public static void go(int index, int sum) {
        if (index == n) {
            check[sum] = true;
            return;
        }

        go(index + 1, sum + arr[index]); // O
        go(index + 1, sum); // X
    }

}