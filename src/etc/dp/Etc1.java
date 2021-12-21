package etc.dp;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 Chapter 9, 플로이드 워셜
 */
public class Etc1 {

    public static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[][] map = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = i == j ? 0 : INF;
                }
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                map[from][to] = 1;
                map[to][from] = 1;
            }

            stringTokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());

            for (int t = 0; t < n; t++) {
                for (int i = 0; i < n; i++) {
                    if (i == t) {
                        continue;
                    }
                    for (int j = 0; j < n; j++) {
                        map[i][j] = Math.min(map[i][j], map[i][t] + map[t][j]);
                    }
                }
            }

            int answer = map[0][k - 1] + map[k - 1][x - 1];
            writer.write(answer >= INF ? "-1" : answer + "");
        }

    }

}