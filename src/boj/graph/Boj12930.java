package boj.graph;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Boj12930 {

    public static int n;

    public static int[][][] w = new int[2][21][21];

    public static int[] dist = new int[21];

    public static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            Arrays.fill(dist, INF);

            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < n; i++) {
                    String s = reader.readLine();

                    for (int j = 0; j < n; j++) {
                        char c = s.charAt(j);
                        if (c == '.') {
                            w[k][i][j] = -1;
                        } else {
                            w[k][i][j] = c - '0';
                        }
                    }
                }
            }


            dijkstra();
            writer.write((dist[1] >= INF ? -1 : dist[1]) + "");
        }
    }

    static void dijkstra() {
        Queue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(0, 0, 0));
        dist[0] = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (dist[current.node] < current.w1 * current.w2) {
                continue;
            }

            for (int adjNode = 0; adjNode < n; adjNode++) {
                if (w[0][current.node][adjNode] != -1) {
                    int tempDist = (current.w1 + w[0][current.node][adjNode]) * (current.w2 + w[1][current.node][adjNode]);

                    if (dist[adjNode] > tempDist) {
                        dist[adjNode] = tempDist;
                        queue.add(new Node(adjNode, current.w1 + w[0][current.node][adjNode], current.w2 + w[1][current.node][adjNode]));
                    }
                }
            }
        }

    }

    public static class Node implements Comparable<Node> {

        private final int node;

        private final int w1;

        private final int w2;

        Node(int node, int W1, int W2) {
            this.node = node;
            this.w1 = W1;
            this.w2 = W2;
        }

        @Override
        public int compareTo(Node o) {
            return this.w1 * this.w2 < o.w1 * o.w2 ? -1 : 1;
        }
    }

}




