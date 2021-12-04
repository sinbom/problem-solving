package boj.brute_force;

import java.io.*;

public class Boj9663 {

    public static int[] arr;

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            arr = new int[n];

            go(0);

            writer.write(answer + "");
        }
    }

    public static void go(int depth) {
        if (depth == arr.length) {
            answer++;
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[depth] = i;
            if (check(depth)) {
                go(depth + 1);
            }
        }
    }

    public static boolean check(int x) {
        for (int i = 0; i < x; i++) {
            if (arr[x] == arr[i]) {
                return false;
            } else if (Math.abs(x - i) == Math.abs(arr[x] - arr[i])) {
                return false;
            }
        }

        return true;
    }

}