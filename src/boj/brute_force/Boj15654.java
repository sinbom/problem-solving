package boj.brute_force;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15654 {

    public static boolean[] check;

    public static int[] arr;

    public static int[] answer;

    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            check = new boolean[n];
            arr = new int[n];
            answer = new int[m];
            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }
            Arrays.sort(arr);
            go(n, m, 0);
            writer.flush();
            writer.close();
        }
    }

    public static void go(int n, int m, int depth) throws IOException {
        if (depth == m) {
            for (int i = 0; i < answer.length; i++) {
                writer.write(answer[i] + "");
                if (i + 1 != answer.length) {
                    writer.write(" ");
                } else {
                    writer.newLine();
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (check[i]) {
                    continue;
                }
                check[i] = true;
                answer[depth] = arr[i];
                go(n, m, depth + 1);
                check[i] = false;
            }
        }

    }

}