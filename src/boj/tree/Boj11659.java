package boj.tree;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11659 {

    public static int[] array;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            stringTokenizer = new StringTokenizer(reader.readLine());
            array = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                array[i] = array[i - 1] + Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                writer.write(array[b] - array[a - 1] + "\n");
            }
        }
    }


}
