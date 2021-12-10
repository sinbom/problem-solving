package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj5014 {

    public static boolean[] check;

    public static String answer = "";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int f = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int g = Integer.parseInt(stringTokenizer.nextToken());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            check = new boolean[f + 1];

            writer.write(bfs(s, g, u, d, f));
        }
    }

    public static String bfs(int s, int g, int u, int d, int f) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{s, 0});
        check[s] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            s = poll[0];
            int c = poll[1];

            if (s == g) {
                return c + "";
            }

            if (s + u <= f && !check[s + u]) {
                check[s + u] = true;
                queue.add(new int[]{s + u, c + 1});
            }

            if (s - d >= 1 && !check[s - d]) {
                check[s - d] = true;
                queue.add(new int[]{s - d, c + 1});
            }
        }
        return "use the stairs";
    }

}