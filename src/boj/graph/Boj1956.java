package boj.graph;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1956 {

    public static final int INF = 987654321;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int e = Integer.parseInt(stringTokenizer.nextToken());
            int[][] arr = new int[v + 1][v + 1];

            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (i != j) {
                        arr[i][j] = INF;
                    }
                }
            }

            for (int i = 0; i < e; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                int c = Integer.parseInt(stringTokenizer.nextToken());

                arr[a][b] = c;
            }

            for (int k = 1; k <= v; k++) {
                for (int i = 1; i <= v; i++) {
                    for (int j = 1; j <= v; j++) {
                        if (i == j) {
                            continue;
                        }

                        if (arr[i][j] > arr[i][k] + arr[k][j]) {
                            arr[i][j] = arr[i][k] + arr[k][j];
                        }
                    }
                }
            }

            int answer = INF;

            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (i == j) {
                        continue;
                    }

                    if (arr[i][j] != INF && arr[j][i] != INF) {
                        answer = Math.min(answer, arr[i][j] + arr[j][i]);
                    }
                }
            }

            answer = (answer == INF) ? -1 : answer;

            writer.write(answer + "\n");
        }
    }

}





