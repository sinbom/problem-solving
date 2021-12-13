package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11048 {

    public static int n;

    public static int m;

    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= m; j++) {
                    int value = Integer.parseInt(stringTokenizer.nextToken());
                    arr[i][j] = value + Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }

            writer.write(arr[n][m] + "");
        }
    }


}