package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1014 {

    private static int n;

    private static int m;

    private static int[][] room;

    private static boolean[][] nodes;

    private static int visitCount;

    private static int[] visit;

    private static int[] matched;

    private static final int[][] scopes = {
        {-1, 1},
        {-1, 0},
        {-1, -1},
        {1, 1},
        {1, 0},
        {1, -1}
    };

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int c = Integer.parseInt(reader.readLine());

            while (c-- > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                n = Integer.parseInt(stringTokenizer.nextToken());
                m = Integer.parseInt(stringTokenizer.nextToken());
                boolean[][] canSit = new boolean[n][m];
                int numbering = 1;
                int broken = 0;
                room = new int[n][m];
                nodes = new boolean[n * m][n * m];
                visitCount = 1;

                for (int i = 0; i < n; i++) {
                    String line = reader.readLine();

                    for (int j = 0; j < m; j++) {
                        room[i][j] = numbering++;

                        if (line.charAt(j) == '.') {
                            canSit[i][j] = true;
                        } else {
                            canSit[i][j] = false;
                            broken++;
                        }
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j += 2) {
                        if (!canSit[i][j]) {
                            continue;
                        }

                        for (int[] scope : scopes) {
                            int no = i + scope[1];
                            int mo = j + scope[0];

                            if (no <= -1 || mo <= -1 || no >= n || mo >= m || !canSit[no][mo]) {
                                continue;
                            }

                            nodes[room[i][j] - 1][room[no][mo] - 1] = true;
                        }
                    }
                }

                writer.write((n * m - broken - bipartite()) + "\n");
            }
        }
    }

    private static int bipartite() {
        int size = 0;
        visit = new int[n * m];
        matched = new int[n * m];

        Arrays.fill(matched, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j += 2) {
                visitCount++;
                size += dfs(room[i][j] - 1);
            }
        }

        return size;
    }

    private static int dfs(int num) {
        if (visit[num] != visitCount) {
            visit[num] = visitCount;

            for (int i = 0; i < n * m; i++) {
                if (!nodes[num][i]) {
                    continue;
                }

                if (matched[i] != -1 && dfs(matched[i]) != 1) {
                    continue;
                }

                matched[i] = num;

                return 1;
            }
        }

        return 0;
    }
}
