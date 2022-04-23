package boj.graph;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1613 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int p = Integer.parseInt(stringTokenizer.nextToken());
            boolean[][] arr = new boolean[n + 1][n + 1];

            for (int i = 0; i < p; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int y = Integer.parseInt(stringTokenizer.nextToken());

                arr[x][y] = true;
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (arr[i][k] && arr[k][j]) {
                            arr[i][j] = true;
                        }
                    }
                }
            }

            int s = Integer.parseInt(reader.readLine());

            StringBuilder sb = new StringBuilder();
            while (s-- > 0) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int y = Integer.parseInt(stringTokenizer.nextToken());

                if (x < 1 || y < 1 || x > n || y > n) {
                    sb.append("0\n");
                } else {
                    if (arr[x][y]) {
                        sb.append("-1\n");
                    } else {
                        if (arr[y][x]) {
                            sb.append("1\n");
                        } else {
                            sb.append("0\n");
                        }
                    }
                }
            }

            writer.write(sb.toString());
        }
    }

}




