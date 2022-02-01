package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17071 {

    public static int n;

    public static int k;

    public static boolean[][] visited = new boolean[500001][2];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            k = Integer.parseInt(stringTokenizer.nextToken());

            if (n == k) {
                writer.write("0");
            } else {
                writer.write(bfs(n) + "");
            }
        }
    }

    public static int bfs(int start) {
        int time = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start][time] = true;

        while (!queue.isEmpty()) {
            if(k > 500000) {
                return -1;
            }

            int newTime = time % 2;

            if(visited[k][newTime]) {
                return time;
            }

            for(int i = 0, size = queue.size(); i < size; i++) {
                int current = queue.poll();
                int nextTime = (time + 1) % 2;
                int next = current - 1;

                if(next >= 0 && !visited[next][nextTime]) {
                    visited[next][nextTime] = true;
                    queue.offer(next);
                }

                next = current + 1;
                if(next < 500001 && !visited[next][nextTime]) {
                    visited[next][nextTime] = true;
                    queue.offer(next);
                }

                next = current * 2;
                if(next < 500001 && !visited[next][nextTime]) {
                    visited[next][nextTime] = true;
                    queue.offer(next);
                }
            }
            time++;
            k += time;
        }

        return -1;
    }
}