package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Boj14226 {

    public static boolean[][] check;

    public static class Node {

        private final int n;

        private final int cp;

        private final int t;

        public Node(int n, int cp, int t) {
            this.n = n;
            this.cp = cp;
            this.t = t;
        }

    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int s = Integer.parseInt(reader.readLine());
            check = new boolean[1001][1001];

            bfs(s);
        }
    }

    public static void bfs(int s) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 0, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.n == s) {
                System.out.println(node.t + "");
                return;
            }

            if (node.n > 0 && node.n <= 1000) {
                if (!check[node.n][node.n]) { // 복사
                    queue.offer(new Node(node.n, node.n, node.t + 1));
                    check[node.n][node.n] = true;
                }
                if (!check[node.n - 1][node.cp]) { // 제거
                    queue.offer(new Node(node.n - 1, node.cp, node.t + 1));
                    check[node.n - 1][node.cp] = true;
                }
            }

            if (node.cp > 0 && node.n + node.cp <= 1000 && !check[node.n + node.cp][node.cp]) {
                queue.offer(new Node(node.n + node.cp, node.cp, node.t + 1));
                check[node.n + node.cp][node.cp] = true;
            }
        }

    }

}