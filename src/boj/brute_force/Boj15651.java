package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj15651 {

    public static int[] arr;

    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            arr = new int[m];
            go(n, m, 0);
            writer.flush();
            writer.close();
        }
    }

    public static void go(int n, int m, int depth) throws IOException {
        if (depth == m) {
            for (int i = 0; i < arr.length; i++) {
                writer.write(arr[i] + "");
                if (i + 1 == arr.length) {
                    writer.newLine();
                } else {
                    writer.write(" ");
                }
            }
        } else {
            for (int i = 1; i <= n; i++) {
                arr[depth] = i;
                go(n, m, depth + 1);
            }
        }

    }

}