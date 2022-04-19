package boj.graph;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1507 {

    public static final int INF = 987654321;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[][] dist = new int[n + 1][n + 1];
            int[][] arr = new int[n + 1][n + 1];
            boolean[][] check = new boolean[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    arr[i][j] = dist[i][j];
                }
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (i == j || i == k || k == j) {
                            continue;
                        }

                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            writer.write("-1\n");
                            return;
                        }

                        if (dist[i][j] == dist[i][k] + dist[k][j]) {
                            arr[i][j] = INF;
                        }
                    }
                }
            }

            int answer = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][j] != INF && i != j && !check[i][j]) {
                        answer += arr[i][j];
                        check[i][j] = check[j][i] = true;
                    }
                }
            }

            writer.write(answer + "\n");
        }
    }

}




