package boj.brute_force;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17825 {

    public static int[] dice;

    public static int[] order;

    public static Node[] horse;

    public static Node start;

    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            dice = new int[10 + 1];
            order = new int[10 + 1];
            horse = new Node[4 + 1];

            for (int i = 1; i <= 10; i++) {
                dice[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            init();
            permutation(1);

            writer.write(answer + "");
        }
    }

    private static void permutation(int depth) {
        if (depth >= 11) {
            answer = Math.max(answer, gameStart());

            return;
        }

        for (int i = 1; i <= 4; i++) {
            order[depth] = i;
            permutation(depth + 1);
        }
    }

    private static int gameStart() {
        Arrays.fill(horse, start);
        int score = 0;

        for (int i = 1; i <= 10; i++) {
            Node cur = horse[order[i]];
            cur.isEmpty = true;
            for (int j = 1; j <= dice[i]; j++) {
                if (j == 1 && cur.fastPath != null) {
                    cur = cur.fastPath;
                } else {
                    cur = cur.next;
                }
            }

            horse[order[i]] = cur;

            if (!cur.isEmpty && !cur.isFinish) {
                score = 0;
                break;
            } else {
                cur.isEmpty = false;
                score += cur.val;
            }
        }

        for (int i = 1; i <= 4; i++) {
            horse[i].isEmpty = true;
        }

        return score;
    }

    private static void init() {
        start = new Node(0);

        Node temp = start;
        for (int i = 2; i <= 40; i += 2) {
            temp = temp.addNext(i);
        }

        Node end = temp.addNext(0);
        end.isFinish = true;
        end.next = end;

        Node crossroads = new Node(25);
        temp = crossroads.addNext(30);
        temp = temp.addNext(35);
        temp.next = Node.getNode(start, 40);

        temp = Node.getNode(start, 10);
        temp = temp.fastPath = new Node(13);
        temp = temp.addNext(16);
        temp = temp.addNext(19);
        temp.next = crossroads;

        temp = Node.getNode(start, 20);
        temp = temp.fastPath = new Node(22);
        temp = temp.addNext(24);
        temp.next = crossroads;

        temp = Node.getNode(start, 30);
        temp = temp.fastPath = new Node(28);
        temp = temp.addNext(27);
        temp = temp.addNext(26);
        temp.next = crossroads;
    }

    public static class Node {

        private final int val;
        private boolean isEmpty;
        private boolean isFinish;
        Node next;
        Node fastPath;

        Node(int val) {
            this.val = val;
            this.isEmpty = true;
        }

        public Node addNext(int value) {
            Node nextNode = new Node(value);
            this.next = nextNode;
            return nextNode;
        }

        public static Node getNode(Node start, int target) {
            Node temp = start;
            while (true) {
                if (temp == null) {
                    return null;
                }
                if (temp.val == target) {
                    return temp;
                }
                temp = temp.next;
            }
        }
    }

}





