package boj.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Boj1948 {

    public static int[] dist;

    public static int[] in;

    public static int n;

    public static int m;

    public static boolean[] visited;

    public static List<Node>[] adj;

    public static List<Node>[] inverse;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            m = Integer.parseInt(reader.readLine());
            in = new int[n + 1];
            dist = new int[n + 1];
            adj = new ArrayList[n + 1];
            inverse = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
                inverse[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                adj[start].add(new Node(end, cost));
                inverse[end].add(new Node(start, cost));
                in[end]++;
            }

            StringTokenizer st = new StringTokenizer(reader.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(s, 0));

            exit:
            while (!q.isEmpty()) {
                Node cur = q.poll();

                for (int i = 0; i < adj[cur.dest].size(); i++) {
                    Node next = adj[cur.dest].get(i);

                    if (cur.cost + next.cost > dist[next.dest]) {
                        dist[next.dest] = cur.cost + next.cost;
                    }

                    in[next.dest]--;

                    if (in[next.dest] == 0) {
                        q.offer(new Node(next.dest, dist[next.dest]));

                        if (next.dest == e) {
                            break exit;
                        }
                    }
                }
            }

            writer.write(dist[e] + "\n");
            visited = new boolean[n + 1];
            q = new LinkedList<>();
            q.offer(new Node(e, dist[e]));

            int answer = 0;

            while (!q.isEmpty()) {
                Node cur = q.poll();

                if (visited[cur.dest]) {
                    continue;
                }

                visited[cur.dest] = true;

                if (cur.dest == s) {
                    break;
                }

                for (int i = 0; i < inverse[cur.dest].size(); i++) {
                    Node next = inverse[cur.dest].get(i);

                    if (cur.cost - next.cost == dist[next.dest]) {
                        answer++;
                        q.offer(new Node(next.dest, cur.cost - next.cost));
                    }
                }
            }

            writer.write(answer + "\n");
        }
    }

    public static class Node {

        private int dest;

        private int cost;

        public Node() {
        }

        public Node(int a, int b) {
            dest = a;
            cost = b;
        }
    }

}



