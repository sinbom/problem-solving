package boj.implement;

import java.io.*;

public class Boj16931 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            int[][] arr = new int[n][m];
            int answer = 0;

            for (int i = 0; i < n; i++) {
                line = reader.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(line[j]);
                    answer += 6 * arr[i][j] - (arr[i][j] - 1) * 2;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i + 1 < n) {
                        answer -= Math.min(arr[i][j], arr[i + 1][j]) * 2;
                    }
                    if (j + 1 < m) {
                        answer -= Math.min(arr[i][j], arr[i][j + 1]) * 2;
                    }
                }
            }

            writer.write(answer + "");
        }
    }
}