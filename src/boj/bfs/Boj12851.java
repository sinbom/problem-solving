package boj.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Boj12851 {

    public static int n;

    public static int k;

    public static int[] time = new int[100001];

    public static int minTime = 987654321;

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            k = Integer.parseInt(stringTokenizer.nextToken());

            if (n >= k) {
                writer.write(n - k + "\n1");
                return;
            }

            bfs();

            writer.write(minTime + "\n" + count);
        }
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        time[n] = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (minTime < time[current]) {
                return;
            }

            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) {
                    next = current + 1;
                } else if (i == 1) {
                    next = current - 1;
                } else {
                    next = current * 2;
                }

                if (next < 0 || next > 100000) {
                    continue;
                }

                if (next == k) {
                    minTime = time[current];
                    count++;
                }

                if (time[next] == 0 || time[next] == time[current] + 1) {
                    queue.add(next);
                    time[next] = time[current] + 1;
                }
            }
        }
    }
}
