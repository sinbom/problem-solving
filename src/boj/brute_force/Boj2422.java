package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2422 {

    public static int n;

    public static int m;

    public static int answer;

    public static boolean[][] graph;

    public static boolean[] visit;

    public static int[] data = new int[3];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            graph = new boolean[n][n];
            visit = new boolean[n];

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int s = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int e = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                graph[s][e] = true;
                graph[e][s] = true;
            }

            go(0, 0);

            writer.write(answer + "");
        }

    }

    private static void go(int index, int depth) {
        if (depth == 3) {
            if (check()) {
                answer++;
            }

            return;
        }

        for (int i = index; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                data[depth] = i;
                go(i, depth + 1);
                visit[i] = false;
            }
        }
    }

    private static boolean check() {
        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (graph[data[i]][data[j]])
                    return false;
            }
        }

        return true;
    }
}
