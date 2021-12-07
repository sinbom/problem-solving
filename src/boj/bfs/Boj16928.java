package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16928 {

    public static boolean[] check = new boolean[101];

    public static int[] move = new int[101];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());

            for (int i = 0; i < n + m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int index = Integer.parseInt(stringTokenizer.nextToken());
                move[index] = Integer.parseInt(stringTokenizer.nextToken());
            }

            writer.write(bfs() + "");
        }
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        int answer = 0;
        queue.add(new int[]{1, 0});
        check[1] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cur = poll[0];
            int cnt = poll[1];

            if (cur == 100) {
                answer = cnt;
                break;
            }

            for (int i = 1; i <= 6; i++) {
                int next = cur + i;

                if (next > 100) {
                    break;
                }

                if (check[next]) {
                    continue;
                }

                check[next] = true;

                if (move[next] != 0) {
                    if (!check[move[next]]) {
                        next = move[next];
                        check[next] = true;
                        queue.add(new int[]{next, cnt + 1});
                    }
                } else {
                    queue.add(new int[]{next, cnt + 1});
                }

            }
        }

        return answer;
    }

}