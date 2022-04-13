package boj.graph;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1389 {

    public static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
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
                stringTokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int y = Integer.parseInt(stringTokenizer.nextToken());

                arr[x][y] = arr[y][x] = 1;
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

            int res = INF;
            int answer = -1;

            for (int i = 1; i <= n; i++) {
                int total = 0;

                for (int j = 1; j <= n; j++) {
                    total += arr[i][j];
                }

                if (res > total) {
                    res = total;
                    answer = i;
                }
            }

            writer.write(answer + "\n");
        }
    }

}




