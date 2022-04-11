package boj.graph;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11404 {

    public static final int INF = 987654321;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            int[][] arr = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = INF;

                    if (i == j) {
                        arr[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                arr[a][b] = Math.min(arr[a][b], c);
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (arr[i][j] > arr[i][k] + arr[k][j]) {
                            arr[i][j] = arr[i][k] + arr[k][j];
                        }
                    }
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][j] == INF) {
                        arr[i][j] = 0;
                    }

                    stringBuilder.append(arr[i][j]).append(" ");
                }
                stringBuilder.append("\n");
            }

            writer.write(stringBuilder.toString());
        }
    }

}



