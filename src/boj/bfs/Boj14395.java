package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj14395 {

    public static Set<Integer> set = new HashSet<>();

    public static String answer = "";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int t = Integer.parseInt(stringTokenizer.nextToken());

            if (s == t) {
                writer.write("0");
                return;
            }

            writer.write(bfs(s, t) + "");
        }
    }

    public static String bfs(int s, int e) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(s, ""));
        set.add(s);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.number == e) {
                return cur.operator;
            }

            if (cur.number <= 34000 && !set.contains(cur.number * cur.number)) {
                set.add(cur.number * cur.number);
                queue.add(new Node(cur.number * cur.number, cur.operator + "*"));
            }

            if (cur.number + cur.number <= 1000000000 && !set.contains(cur.number + cur.number)) {
                set.add(cur.number + cur.number);
                queue.add(new Node(cur.number + cur.number, cur.operator + "+"));
            }

            if (!set.contains(1)) {
                set.add(1);
                queue.add(new Node(1, cur.operator + "/"));
            }
        }

        return "-1";
    }

    public static class Node {

        private final int number;
        private final String operator;

        public Node(int number, String operator) {
            this.number = number;
            this.operator = operator;
        }

    }

}