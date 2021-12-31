package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj17089 {
    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[][] relations = new int[n + 1][n + 1];
            int[] friend = new int[n + 1];

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());

                relations[a][b] = 1;
                relations[b][a] = 1;
                friend[a]++;
                friend[b]++;
            }

            int min = Integer.MAX_VALUE;

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    if (k == i || relations[k][i] != 1) {
                        continue;
                    }

                    for (int j = 1; j <= n; j++) {
                        int cnt = 0;

                        if (j == i || j == k || relations[i][j] != 1 || relations[j][k] != 1) {
                            continue;
                        }

                        cnt += friend[i] - 2;
                        cnt += friend[j] - 2;
                        cnt += friend[k] - 2;
                        min = Math.min(cnt, min);
                    }
                }
            }

            if (min == Integer.MAX_VALUE) {
                min = -1;
            }

            writer.write(min + "");
        }
    }
}
