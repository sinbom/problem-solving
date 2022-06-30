package boj.dp;

import java.io.*;
import java.util.*;

public class Boj2157 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int M = Integer.parseInt(stringTokenizer.nextToken());
            int K = Integer.parseInt(stringTokenizer.nextToken());
            int[][] dp = new int[M + 1][N + 1];
            List<Node>[] boards = new List[N + 1];

            for (int i = 0; i <= N; i++) {
                boards[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                int c = Integer.parseInt(stringTokenizer.nextToken());

                if (a > b) {
                    continue;
                }

                boards[a].add(new Node(b, c));
            }

            int result = 0;

            Queue<Integer> q = new LinkedList<>();

            q.add(1);
            int cnt = 1;

            while (!q.isEmpty() && cnt < M) {
                int size = q.size();

                while (size-- > 0) {
                    int nowIndex = q.poll();
                    for (Node nextNode : boards[nowIndex]) {

                        int nextIndex = nextNode.index;
                        int nextScore = dp[cnt][nowIndex] + nextNode.score;

                        if (dp[cnt + 1][nextIndex] >= nextScore) {
                            continue;
                        }

                        dp[cnt + 1][nextIndex] = nextScore;

                        q.add(nextIndex);

                    }

                }
                cnt++;
            }

            for (int i = 2; i <= M; i++) {
                result = Integer.max(result, dp[i][N]);
            }

            writer.write(result + "");
        }

    }

    public static class Node {

        private final int index;

        private final int score;

        public Node(int index, int score) {
            this.index = index;
            this.score = score;
        }
    }

}

