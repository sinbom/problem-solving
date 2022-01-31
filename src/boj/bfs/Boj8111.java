package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Boj8111 {

    public static final int MAX = 20000;

    public static boolean[] visited;

    public static int[] parent;

    public static Map<Integer, Character> map;

    public static int t;

    public static int n;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            t = Integer.parseInt(reader.readLine());

            for (int i = 0; i < t; ++i) {
                n = Integer.parseInt(reader.readLine());
                map = new HashMap<>();
                visited = new boolean[MAX];
                parent = new int[MAX];

                bfs();
                print(0);
                System.out.println();
            }
        }
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        parent[1] = -1;
        visited[1] = true;
        map.put(1, '1');

        while (!queue.isEmpty()) {
            int current = queue.poll();

            int[] next = {current * 10 % n, (current * 10 + 1) % n};

            for (int i = 0; i < next.length; i++) {
                if (visited[next[i]]) {
                    continue;
                }

                map.put(next[i], (char) (i + '0'));
                parent[next[i]] = current;

                if (next[i] == 0) {
                    return;
                }

                visited[next[i]] = true;
                queue.offer(next[i]);
            }
        }
    }

    private static void print(int idx) {
        if (idx == -1) {
            return;
        }

        print(parent[idx]);
        System.out.print(map.get(idx));
    }
}